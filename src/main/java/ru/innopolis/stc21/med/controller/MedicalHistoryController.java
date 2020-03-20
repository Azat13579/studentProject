package ru.innopolis.stc21.med.controller;

import com.rabbitmq.client.*;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.stc21.med.exception.RecordNotFoundException;
import ru.innopolis.stc21.med.model.MedicalHistoryEntity;
import ru.innopolis.stc21.med.model.UsersEntity;
import ru.innopolis.stc21.med.service.GeoIPService;
import ru.innopolis.stc21.med.service.MailSender;
import ru.innopolis.stc21.med.service.MedicalHistoryService;
import ru.innopolis.stc21.med.service.UserService;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Controller
public class MedicalHistoryController {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private Environment env;

    @Autowired
    private UserService usersService;
    @Autowired
    private MedicalHistoryService medicalHistoryService;
    @Autowired
    private MailSender mailSender;

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://medbrat.ml:5432/projectdb");
        dataSource.setUsername("winner");
        dataSource.setPassword("ySFG1YRXZm3Pu5V");
        return dataSource;
    }


    @RequestMapping(value = "/getNeiroResponse", method = RequestMethod.GET)
    public @ResponseBody
    JsonResponse getNeiroResponse(@RequestParam String iid) throws RecordNotFoundException, InterruptedException {
        if ((iid == null) || (iid.equals(""))) return null;

        JsonResponse resp = new JsonResponse();
        MedicalHistoryEntity history = medicalHistoryService.getById(Long.valueOf(iid));
        while (history.getNeiro_diagtose().equals(" ")){
            TimeUnit.SECONDS.sleep(3);

            try (java.sql.Connection connection = getDataSource().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(
                                     "SELECT neiro_diagtose, accuracy FROM medical_history " +
                                             "WHERE id=?")) {
                    preparedStatement.setLong(1, Long.valueOf(iid));
                    preparedStatement.executeQuery();
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        if (!resultSet.getString("neiro_diagtose").equals(" ")) {
                            resp.setNeiro_diagnose(resultSet.getString("neiro_diagtose"));
                            resp.setAccuracy(resultSet.getString("accuracy"));
                            return resp;
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        resp.setNeiro_diagnose(history.getNeiro_diagtose());
        resp.setAccuracy(history.getAccuracy());
        return resp;
    }

    @GetMapping({"/"})
    public String historymain(Model model,
                              @RequestParam(value = "name", required = false, defaultValue = "World") String name) throws RecordNotFoundException, FileNotFoundException {

        UsersEntity currentUser = usersService.getUserByName(getCurrentUsername());
        List<MedicalHistoryEntity> medHistories = medicalHistoryService.getAllByUser(currentUser);
        //System.out.println("");
        model.addAttribute("medHistories", medHistories);
        return "history";
    }

    @GetMapping({"/history"})
    public String history(Model model,
                          @RequestParam(value = "name", required = false, defaultValue = "World") String name) throws RecordNotFoundException, FileNotFoundException {

        UsersEntity currentUser = usersService.getUserByName(getCurrentUsername());
        List<MedicalHistoryEntity> medHistories = medicalHistoryService.getAllByUser(currentUser);

        model.addAttribute("medHistories", medHistories);
        return "history";
    }

    @PostMapping({"/history"})
    public String historyPost(Model model,
                              @RequestBody MultiValueMap<String, String> formData
    ) throws RecordNotFoundException {
        if (formData.containsKey("deleteAction")) {
            List<String> strings = formData.get("customDel");
            if (strings != null) {
                long[] delIds = strings.stream().mapToLong(Long::valueOf).toArray();
                for (long delId : delIds) {
                    medicalHistoryService.deleteHistoryById(delId);
                }
            }
        } else if (formData.containsKey("sendEmail")) {
            UsersEntity user = usersService.getUserByName(getCurrentUsername());
            List<String> idsHistiry = formData.get("customMail");
            if (idsHistiry != null) {
                String message = buildMessage(idsHistiry);
                mailSender.send(user.getEmail(), "Результаты анализов порталом MedBrat.ml", message);
            }
        }

        return "redirect:/history";
    }

    private String buildMessage(List<String> idsHistiry) throws RecordNotFoundException {
        UsersEntity currentUser = usersService.getUserByName(getCurrentUsername());
        String message = "Уважаемый(ая), " + currentUser.getFirst_name() + " " + currentUser.getSecond_name() + "\n";

        long[] mailIds = idsHistiry.stream().mapToLong(Long::valueOf).toArray();

        for (Long id : mailIds) {
            MedicalHistoryEntity history = medicalHistoryService.getHistoryById(id);
            Date date_visit = history.getDate_visit();
            String neiro_diagtose = history.getNeiro_diagtose();
            String accuracy = history.getAccuracy();
            message += "На ваше обращение поданное " + date_visit + ". Ваш диагноз нейросети: " + neiro_diagtose + " с вероятностью " + accuracy + "%.\n";
        }
        message += "\n С Уважением, команда МедБрат!";
        return message;
    }

    @GetMapping({"/addRequest"})
    public String hello(Model model,
                        @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        // model.addAttribute("name", name);
        return "addRequest";
    }


    private void sender(String message) throws IOException, TimeoutException {
        String QUEUE_NAME = env.getProperty("rabbit.queueToPyhton");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(env.getProperty("rabbit.user"));
        factory.setPassword(env.getProperty("rabbit.password"));
        factory.setHost(env.getProperty("rabbit.host"));
        factory.setPort(Integer.parseInt(env.getProperty("rabbit.port")));
        factory.setVirtualHost(env.getProperty("rabbit.virtualHost"));
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }


    @PostMapping({"/addRequest"})
    public String hello(Model model,
                        @RequestParam(value = "name", required = false, defaultValue = "World") String name,
                        @RequestParam String comment,
                        @RequestParam("file") MultipartFile file) throws IOException, RecordNotFoundException, TimeoutException {
        Long iid = 0L;
        String fullPath = "";
        if (!file.isEmpty()) {
            String imgPath = uploadPath;
            //URL imgPath = getClass().getResource("/static/uploads");
            //File imgPath = ResourceUtils.getFile("classpath:uploads");
            File uploadDir = new File(imgPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            UsersEntity currentUser = usersService.getUserByName(getCurrentUsername());

            MedicalHistoryEntity mHistory = medicalHistoryService.create(new Date(), currentUser);
            mHistory.setComment(comment == null ? "" : comment);

            String fileName = file.getOriginalFilename();
            String extension = "";

            int i = fileName.lastIndexOf('.');
            if (i > 0) {
                extension = fileName.substring(i + 1);
            }

            String fName = mHistory.getId() + "." + extension;
            fullPath = imgPath + "/" + fName; //file.getOriginalFilename();
            mHistory.setImgName(fName);
            medicalHistoryService.save(mHistory);
            file.transferTo(new File(fullPath));
            iid = mHistory.getId();
        }

        name = "";

        byte[] fileContent = FileUtils.readFileToByteArray(new File(fullPath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        JSONObject obj = new JSONObject();
        obj.put("id", String.valueOf(iid));
        obj.put("picture", encodedString);
        sender(obj.toJSONString());
        return "redirect:/history";
    }
}

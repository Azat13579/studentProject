package ru.innopolis.stc21.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc21.med.exception.RecordNotFoundException;
import ru.innopolis.stc21.med.model.UsersEntity;
import ru.innopolis.stc21.med.repository.UserRepository;
import ru.innopolis.stc21.med.service.UserService;


@Controller
public class SecurityController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService usersService;

    @GetMapping("/login")
    public String login(@RequestParam(name="name", required=false, defaultValue="Username") String name, Model model) {
        model.addAttribute("name", name);
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
      //  UsersEntity userFromDB = usersService.getUserByName(usersEntity.getUsername());
     //   if(userFromDB != null){
           // model.
     //   }
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam(name="username", required=false, defaultValue="") String username,
            @RequestParam(name="password", required=false, defaultValue="") String password,
            @RequestParam(name="first_name", required=false, defaultValue="") String first_name ,
            @RequestParam(name="second_name", required=false, defaultValue="") String second_name,
            @RequestParam(name="last_name", required=false, defaultValue="") String last_name,
            @RequestParam(name="email", required=false, defaultValue="") String email,
            @RequestParam(name="snils", required=false, defaultValue="") String snils,
                          Model model) throws RecordNotFoundException {

        UsersEntity usersEntity = userRepository.findByUsername(username);
        if(usersEntity != null){
            //usersEntity.setUsername(username);
            //usersEntity.setPassword(password);
            //usersService.save(usersEntity);
            return "redirect:/history";
        } else {
            usersService.create(username, password, first_name, second_name, last_name, email, snils);
            return "redirect:/history";
        }
    }
}

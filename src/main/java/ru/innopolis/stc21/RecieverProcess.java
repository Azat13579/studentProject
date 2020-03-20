package ru.innopolis.stc21;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.Gson;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.innopolis.stc21.med.service.JsonUser;

import javax.sql.DataSource;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class RecieverProcess {
    @Autowired
    private Environment env;

    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://medbrat.ml:5432/projectdb");
        dataSource.setUsername("winner");
        dataSource.setPassword("ySFG1YRXZm3Pu5V");
        return dataSource;
    }

    public void putToBase(String message) throws SQLException, IOException {
        Reader reader = new StringReader(message);
        Gson g = new Gson();
        JsonUser jsonUser = g.fromJson(message, JsonUser.class);

        Long id;
        String result;
        String accuracy;

        id = jsonUser.getId();
        result = jsonUser.getResult();
        accuracy = jsonUser.getAccuracy();

        try (java.sql.Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE medical_history SET neiro_diagtose=?, accuracy=? WHERE id=?")) {
                connection.setAutoCommit(false);
                preparedStatement.setString(1, result);
                preparedStatement.setString(2, accuracy);
                preparedStatement.setLong(3, id);
                preparedStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public   RecieverProcess() throws IOException, TimeoutException {
      /*  try (InputStream input = new FileInputStream("path/to/config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("db.url"));
            System.out.println(prop.getProperty("db.user"));*/

        String QUEUE_NAME = "tojava";
        ConnectionFactory factory = new ConnectionFactory();

     /*   factory.setUsername(env.getProperty("rabbit.user"));
        factory.setPassword(env.getProperty("rabbit.password"));
        factory.setHost(env.getProperty("rabbit.host"));
        factory.setPort(Integer.parseInt(env.getProperty("rabbit.port")));
        factory.setVirtualHost(env.getProperty("rabbit.virtualHost"));*/

        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("medbrat.ml");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        final String[] message = new String[1];
        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery delivery) throws IOException {
                message[0] = new String(delivery.getBody(), "UTF-8");
                try {
                    putToBase(message[0]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("Received ".concat(message[0]));
            }
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        });
        //return message[0];
    }
}

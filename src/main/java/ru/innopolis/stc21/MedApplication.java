package ru.innopolis.stc21;

import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class MedApplication {

    public static void main(String[] args) throws IOException, TimeoutException {
        SpringApplication.run(MedApplication.class, args);
        RecieverProcess rp = new RecieverProcess();
    }

}

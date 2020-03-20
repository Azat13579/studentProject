package ru.innopolis.stc21.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.stc21.med.exception.RecordNotFoundException;
import ru.innopolis.stc21.med.model.UsersEntity;
import ru.innopolis.stc21.med.service.UserService;

@RestController
public class RESTUserController {
    private static final String template = "Hello, %s!";

    @Autowired
    UserService usersService;


    @RequestMapping("/test")
    public UsersEntity greeting(@RequestParam(value="name", defaultValue="World") String name) throws RecordNotFoundException {
       // UsersEntity userTest = usersService.create("Vasya", "123", "snils 123");
        return usersService.getUserById(1L);
    }
}

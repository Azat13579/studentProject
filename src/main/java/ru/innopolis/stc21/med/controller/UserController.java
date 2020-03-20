package ru.innopolis.stc21.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.stc21.med.exception.RecordNotFoundException;
import ru.innopolis.stc21.med.model.UsersEntity;
import ru.innopolis.stc21.med.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService usersService;

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @GetMapping
    public String userProfile(Model model) throws RecordNotFoundException {

        UsersEntity currentUser = usersService.getUserByName(getCurrentUsername());
        model.addAttribute("user", currentUser);
        return "userProfile";
    }

    @GetMapping("{user}")
    public String userEdit(@PathVariable UsersEntity user, Model model) throws RecordNotFoundException {
        model.addAttribute("user", user);
        return "userEdit";
    }

    @PostMapping
    public String addUser(
            @RequestParam String password,
            @RequestParam String first_name,
            @RequestParam String second_name,
            @RequestParam String last_name,
            @RequestParam String email,
            @RequestParam String snils) throws RecordNotFoundException {

        UsersEntity userEdit = usersService.getUserByName(getCurrentUsername());
        userEdit.setPassword(password);
        userEdit.setFirst_name(first_name);
        userEdit.setSecond_name(second_name);
        userEdit.setLast_name(last_name);
        userEdit.setEmail(email);
        userEdit.setSnils(snils);
        usersService.save(userEdit);
        return "redirect:/user";
    }
}

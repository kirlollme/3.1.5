package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.UserService.RoleService;
import ru.kata.spring.boot_security.demo.UserService.UserService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collections;
import java.util.HashSet;

@org.springframework.stereotype.Controller
@RequestMapping("/")
@AllArgsConstructor
public class Controller {
    private final UserService userService;
    private final RoleService roleService;
    @GetMapping("/")
    public String home() {

//        User user = new User("admin","admin",12,"admin","admin");
//        user.setRoles( new HashSet<>(Collections.singleton(new Role("ROLE_ADMIN"))) );
//        userService.addUser(user);
        return "redirect:/login";
    }

}
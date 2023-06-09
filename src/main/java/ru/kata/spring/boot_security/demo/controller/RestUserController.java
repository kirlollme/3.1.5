package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.UserService.UserService;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class RestUserController {

    private final UserService userService;

    @GetMapping
    public User getAuthorisedUser (Principal principal) {

        return  userService.findByUsername(principal.getName());
    }
}

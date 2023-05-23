package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.UserService.UserService;

import ru.kata.spring.boot_security.demo.model.User;


import javax.validation.Valid;


@Validated
@Controller
public class LogRegController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login")
    public String loginPage() {

        return "login";
    }
    @GetMapping(value = "/registration")
    public String regPage(@ModelAttribute("user") User user) {
        return "registration";
    }
    @PostMapping(value ="/registration")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.addUser( user );
        return "redirect:/";
    }

}

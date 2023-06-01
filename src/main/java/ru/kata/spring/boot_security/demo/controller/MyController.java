package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.UserService.RoleService;
import ru.kata.spring.boot_security.demo.UserService.UserService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;

@Controller
public class MyController {


    @GetMapping("/")
    public String home() {

        return "redirect:/login";
    }
    @GetMapping("/admin")
    public String admin() {

        return "admin";
    }

    @GetMapping("/user")
    public String user() {

        return "user";
    }
    public UserDetails loadUserByUsername(String username, UserRepository userRepository) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        user.getAuthorities().size();
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Пользователь с username = '%s' не найден", username));
        }
        return user.fromUser();
    }
}
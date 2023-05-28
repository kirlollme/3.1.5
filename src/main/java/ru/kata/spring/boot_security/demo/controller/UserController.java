package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.UserService.UserService;
import ru.kata.spring.boot_security.demo.model.User;


@Validated
@Controller

public class UserController {
	private UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "user")
	public String userInfo(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("roles", userService.getUserByUsername(user.getUsername()).getRoles());
		model.addAttribute("user", userService.getUserByUsername(user.getUsername()));
		return "user";
	}
}
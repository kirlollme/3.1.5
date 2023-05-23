package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.kata.spring.boot_security.demo.UserService.UserService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Validated
@Controller

public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin")
    public String printWelcome(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @RequestMapping(value = "/admin/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping(value = "/admin/create")
    public String create(@ModelAttribute("user")  User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";

        userService.addUser(user);
        return "redirect:/admin";
    }
    @RequestMapping(value = "/admin/show", method = RequestMethod.GET)
    public String show(Model model , @RequestParam Long id ) {
        model.addAttribute("user", userService.getById(id));
        return "showUser";
    }
    @RequestMapping(value = "/admin/edit",method = RequestMethod.GET)
    public String edit(Model model,
                       @RequestParam(value = "id", defaultValue = "1") Long id ) {
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }

    @PostMapping(value = "/admin/delete")
    @ResponseBody
    public RedirectView delete(@RequestParam(value = "id", defaultValue = "1") Long id) {
        userService.deleteUser(id);
        return new RedirectView("/admin");
    }
    @RequestMapping(value = "/admin/edit")
    @ResponseBody
    public RedirectView edit(
            @ModelAttribute("user")  User user,
            @RequestParam(value = "id") Long id) {
        userService.changeDataUser(id,user);
        return new RedirectView("/admin");
    }

}
package ru.kata.spring.boot_security.demo.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.UserService.UserService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor

public class RestAdminController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        final List<User> allUsers = userService.getUsers();
        return allUsers != null && !allUsers.isEmpty()
                ? new ResponseEntity<>(allUsers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity <User> getUserById (@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }
    @PostMapping
    public ResponseEntity<User> addUser (@RequestBody User user) {

        return ResponseEntity.ok(userService.addUser(user));
    }
    @PutMapping ("/{id}")
    public ResponseEntity<User> editUser (@RequestBody User user, @PathVariable ("id") Long id) {
        return ResponseEntity.ok(userService.changeDataUser( user));
    }

    @DeleteMapping( "/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable ("id") Long id) {

        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

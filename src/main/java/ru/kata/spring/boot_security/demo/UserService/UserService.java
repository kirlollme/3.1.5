package ru.kata.spring.boot_security.demo.UserService;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
@Repository
public interface UserService extends UserDetailsService {


    void addUser(User user);

    List<User> getUsers();
    void deleteUser(Long id);
    void changeDataUser(Long id, User userAfter);
    User getById(Long id);

    User getUserByUsername(String username);
}

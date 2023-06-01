package ru.kata.spring.boot_security.demo.UserService;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

@Repository
public interface UserService extends UserDetailsService {


    User addUser(User user);

    List<User> getUsers();
    void deleteUser(Long id);
    User changeDataUser(User userAfter);
    User getById(Long id);

    User findByUsername(String username);

}

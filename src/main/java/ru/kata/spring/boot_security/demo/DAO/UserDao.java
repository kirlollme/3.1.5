package ru.kata.spring.boot_security.demo.DAO;




import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao extends UserDetailsService {
    void addUser(User user);
    List<User> getUsers();
    void deleteUser(Long id);
    void changeDataUser(Long id, User userAfter);

    User getById(Long id);

    User getUserByUsername(String username);
}

package ru.kata.spring.boot_security.demo.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import org.apache.commons.collections4.IteratorUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service


@Transactional
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp( UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Override
    @Transactional
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {

        return IteratorUtils.toList(userRepository.findAll().iterator());
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User existingUser = (User) userRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Пользователь не найден с помощью метода удаления в классе UserServiceImp"));
        userRepository.delete(existingUser);
    }

    @Override
    @Transactional
    public User changeDataUser(User userAfter) {
        return userRepository.save(userAfter);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return (User) userRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Пользователь с таким id не найден в классе UserServiceImp"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }
}

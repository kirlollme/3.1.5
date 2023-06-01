package ru.kata.spring.boot_security.demo.UserService;

import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleService {
    void add (Role role);

    Role getById (Long id);

}

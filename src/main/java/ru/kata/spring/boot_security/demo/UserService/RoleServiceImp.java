package ru.kata.spring.boot_security.demo.UserService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

@Service
@AllArgsConstructor
public class RoleServiceImp implements RoleService{


    private final RoleRepository roleRepository;

    @Override
    public void add(Role role) {

        roleRepository.save(role);
    }
    @Override
    public Role getById(Long id) {

        return roleRepository.getById(id);
    }


}
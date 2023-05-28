package ru.kata.spring.boot_security.demo.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

@Service
@Transactional
public class RoleServiceImp implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImp(RoleDao roleDao) {

        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> getAllRoles() {

        return roleDao.getAllRoles();
    }

    @Override
    public Role getRoleByName(String name) {

        return roleDao.getRoleByName(name);
    }

    @Override
    public Set<Role> getSetOfRoles(String[] roleNames) {
        return
                roleDao.getSetOfRoles(roleNames);
    }

    @Override
    public void add(Role role) {

        roleDao.add(role);
    }

    @Override
    public void edit(Role role) {

        roleDao.edit(role);
    }
    @Override
    public Role getById(Long id) {

        return roleDao.getById(id);
    }
}
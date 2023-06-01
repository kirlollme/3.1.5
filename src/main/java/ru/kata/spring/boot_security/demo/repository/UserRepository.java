package ru.kata.spring.boot_security.demo.repository;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
@EntityScan(basePackages = "ru.kata.spring.boot_security.demo.model")
public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    User findUserByUsername(String username);


    Optional<Object> findById(Long id);

}

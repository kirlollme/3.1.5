package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.kata.spring.boot_security.demo.UserService.RoleService;
import ru.kata.spring.boot_security.demo.UserService.UserService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
//		ApplicationContext context = SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
//		try {
//			UserService userService = context.getBean(UserService.class);
//			RoleService roleService = context.getBean(RoleService.class);
//
//			User user1 = new User("admin", "admin", 25, "admin@mail.ru", "admin");
//			User user2 = new User("user", "user", 33, "user@mail.ru", "user");
//			User user3 = new User("ivan", "ivan", 40, "ivan@mail.ru", "ivan");
//
//			Role roleAdmin = new Role("ROLE_ADMIN");
//			Role roleUser = new Role("ROLE_USER");
//
//			Set<Role> rolesAdmUs = new HashSet<>();
//			rolesAdmUs.add(roleAdmin);
//			rolesAdmUs.add(roleUser);
//
//			Set<Role> rolesUs = new HashSet<>();
//			rolesUs.add(roleUser);
//
//			user1.setRoles(rolesAdmUs);
//			user2.setRoles(rolesUs);
//			user3.setRoles(rolesUs);
//
//			roleService.add(roleAdmin);
//			roleService.add(roleUser);
//
//			userService.addUser(user1);
//			userService.addUser(user2);
//			userService.addUser(user3);
//
//		} catch (Exception ignored) {
//		}
	}

}

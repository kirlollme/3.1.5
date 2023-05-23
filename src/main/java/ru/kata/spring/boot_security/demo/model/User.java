package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @Column(name = "username")
    @Size(min=2, message = "Не меньше 5 знаков")
    private String username;

    @Column(name = "password")
    @Size(min=2, message = "Не меньше 5 знаков")
    private String password;

    @Column(name = "age")
    private int age;
    @Column(name = "active")
    private boolean active = true;


    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable ( name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id")
                ,inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Role  role) {

        if (roles == null){
            roles = new HashSet<>();
        }
        this.roles.add(role);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String name, String surname, int age) {
        this.age = age;
        this.name = name;
        this.surname = surname;
    }
    public User(String name, String surname, int age,String username,String password) {
        this.age = age;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;

    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

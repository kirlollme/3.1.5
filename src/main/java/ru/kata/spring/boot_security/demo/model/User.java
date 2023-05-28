package ru.kata.spring.boot_security.demo.model;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) // конструктор без параметров нужен только хибернейту
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @Column(name = "username",unique = true, length = 100)
    @Size(min=2, message = "Не меньше 5 знаков")
    private String username;

    @Column(name = "password")
    @Size(min=2, message = "Не меньше 5 знаков")
    private String password;
    @Column(name = "age")
    private int age;
    @Column(name = "active")
    private boolean active = true;


    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinTable ( name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();




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





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
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
    public UserDetails fromUser() {
        return new org.springframework.security.core.userdetails.User(username, password, getAuthorities());
    }
}

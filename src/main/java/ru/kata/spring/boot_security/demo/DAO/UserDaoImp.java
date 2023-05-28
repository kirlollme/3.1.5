package ru.kata.spring.boot_security.demo.DAO;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional
    public void addUser(User user) {
//        Set<Role> role= new HashSet<>();
//        role.add(new Role("ROLE_USER"));
//        user.setRoles(role);
        entityManager.persist(user);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Query query = (Query) entityManager.createQuery("select a from User a", User.class);
        return query.getResultList();
    }

    @Override
    public void deleteUser(Long id) {
        String hql = "DELETE User WHERE id = :ID";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("ID", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void changeDataUser(Long id, User userAfter) {
        userAfter.setId(id);
        userAfter.setPassword(userAfter.getPassword());
        entityManager.merge(userAfter);
    }

    @Override
    public User getById(Long id) {
        String hql = "from User WHERE id = :id";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("id", id);
        return (User) query.uniqueResult();
    }

    @Override
    public User getUserByUsername(String username) {
        String hql = "from User WHERE username = :username";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String hql = "from User WHERE username = :username";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }
}

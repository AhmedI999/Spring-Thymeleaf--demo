package com.springboot.thymeleafsecuritydemo.dao;

import com.springboot.thymeleafsecuritydemo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements UserDao {
    private EntityManager entityManager;

    @Override
    public User findByUserName(String userName) {
        TypedQuery<User> userQuery = entityManager.createQuery("from User where userName=:uName", User.class);
        userQuery.setParameter("uName", userName);
        User user;
        try {
            user = userQuery.getSingleResult();
        } catch (Exception e){
            System.out.println("User not found");
            user = null;
        }
        return user;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.merge(user);
    }
}

package com.springboot.thymeleafsecuritydemo.dao;

import com.springboot.thymeleafsecuritydemo.entity.User;

public interface UserDao {
    User findByUserName(String userName);

    void save(User user);
}

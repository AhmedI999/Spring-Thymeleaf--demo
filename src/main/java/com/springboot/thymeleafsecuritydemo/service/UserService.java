package com.springboot.thymeleafsecuritydemo.service;

import com.springboot.thymeleafsecuritydemo.entity.User;
import com.springboot.thymeleafsecuritydemo.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);

    void save(WebUser webUser);
}


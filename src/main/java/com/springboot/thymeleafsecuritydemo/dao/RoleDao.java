package com.springboot.thymeleafsecuritydemo.dao;

import com.springboot.thymeleafsecuritydemo.entity.Roles;

public interface RoleDao {
    Roles findRoleByName(String name);
}

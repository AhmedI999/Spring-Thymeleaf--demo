package com.springboot.thymeleafsecuritydemo.dao;

import com.springboot.thymeleafsecuritydemo.entity.Roles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class RoleDaoImpl implements RoleDao {
    private EntityManager entityManager;

    @Override
    public Roles findRoleByName(String name) {
        TypedQuery<Roles> rolesQuery =
                entityManager.createQuery("from Roles where name=:roleName", Roles.class);
        rolesQuery.setParameter("roleName", name);
        Roles role;
        try {
            role = rolesQuery.getSingleResult();
        } catch (Exception e){
            System.out.println(" User Role not found");
            role = null;
        }
        return role;
    }
}

package com.springboot.thymeleafsecuritydemo.service;

import com.springboot.thymeleafsecuritydemo.dao.RoleDao;
import com.springboot.thymeleafsecuritydemo.dao.UserDao;
import com.springboot.thymeleafsecuritydemo.entity.Roles;
import com.springboot.thymeleafsecuritydemo.entity.User;
import com.springboot.thymeleafsecuritydemo.user.WebUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private RoleDao roleDao;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public void save(WebUser webUser) {
        User user = new User();
        user.setUserName(webUser.getUserName());
        user.setPassword(passwordEncoder.encode(webUser.getPassword()));
        user.setFirstName(webUser.getFirstName());
        user.setLastName(webUser.getLastName());
        user.setEmail(webUser.getEmail());
        user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));
        userDao.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUserName(userName);
        if (user == null){
            throw new UsernameNotFoundException("User not found!");
        }
        Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                authorities);
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Roles tempRole : roles) {
            SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getName());
            authorities.add(tempAuthority);
        }

        return authorities;
    }
}

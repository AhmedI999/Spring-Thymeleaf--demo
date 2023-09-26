package com.springboot.thymeleafsecuritydemo.security;

import com.springboot.thymeleafsecuritydemo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(request -> request
                        .requestMatchers("/").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                        .requestMatchers("/managers").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/admins").hasRole("ADMIN")
                        .requestMatchers("/denied").hasAnyRole("EMPLOYEE", "MANAGER")
                        .requestMatchers("/managers/**").hasAnyRole("MANAGER")
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/processRegistration").permitAll().anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll())
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(handle -> handle.accessDeniedPage("/denied"));
        return http.build();
    }
}

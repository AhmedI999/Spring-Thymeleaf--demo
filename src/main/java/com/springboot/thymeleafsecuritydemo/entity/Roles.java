package com.springboot.thymeleafsecuritydemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.lang.annotation.Target;

@Entity
@Table(name = "role")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    public Roles(String name) {
        this.name = name;
    }
}

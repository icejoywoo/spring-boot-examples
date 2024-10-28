package com.example.springboot.mysql_demo.dao;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="student")
public class Student {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="age")
    private int age;
}

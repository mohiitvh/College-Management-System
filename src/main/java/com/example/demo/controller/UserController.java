package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.UserEntity;
import com.example.demo.repo.UserRepo;

@RestController
@RequestMapping("/students")
public class UserController {

    @Autowired
    private UserRepo repo;

    @PostMapping
    public UserEntity addUser(@RequestBody UserEntity student) {
        return repo.save(student);
    }

    @GetMapping
    public List<UserEntity> getAll() {
        return repo.findAll();
    }
    
    
}
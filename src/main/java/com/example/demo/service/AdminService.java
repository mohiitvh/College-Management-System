package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repo.AdminRepo;

@Service
public class AdminService {

    @Autowired
    private AdminRepo repo;

    public Admin save(Admin admin){
        return repo.save(admin);
    }

    public Admin login(String email,String password){
        Admin admin = repo.findByEmail(email);

        if(admin != null &&
           admin.getPassword().equals(password)){
            return admin;
        }

        return null;
    }
}
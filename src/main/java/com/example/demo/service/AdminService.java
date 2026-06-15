package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repo.AdminRepo;

@Service
public class AdminService {

    @Autowired
    private AdminRepo repo;

    private static final String SECRET_ADMIN_PASS = "admin@123";
    
    

    public String register(Admin admin) {

        if(!SECRET_ADMIN_PASS.equals(admin.getAdminPass())) {
            return "INVALID_ADMIN_PASS";
        }

        repo.save(admin);

        return "SUCCESS";
    }

    public Admin login(String email,String password){

        Admin admin = repo.findByEmail(email);

        if(admin != null &&
           admin.getPassword().equals(password)) {

            return admin;
        }

        return null;
    }

    public Admin findById(Long id){
        return repo.findById(id).orElse(null);
    }
    
}
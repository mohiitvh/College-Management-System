package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Staff;
import com.example.demo.repo.StaffRepo;

@Service
public class StaffService {

    @Autowired
    private StaffRepo repo;

    // ================= SAVE =================
    public Staff save(Staff staff){
        return repo.save(staff);
    }

    // ================= LOGIN =================
    public Staff login(String email,String password){

        Staff staff = repo.findByEmail(email);

        if(staff != null &&
           staff.getPassword().equals(password)){
            return staff;
        }

        return null;
    }

    // ================= FIND BY ID =================
    public Staff findById(Long id){
        return repo.findById(id).orElse(null);
    }

    // ================= ALL STAFF (NEW) =================
    public List<Staff> findAll(){
        return repo.findAll();
    }
}
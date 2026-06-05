package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AdmissionEntity;
import com.example.demo.repo.AdmissionRepo;

@Service
public class AdmissionService {

    @Autowired
    private AdmissionRepo admissionRepo;

    public AdmissionEntity save(AdmissionEntity admission) {
        return admissionRepo.save(admission);
    }

    public AdmissionEntity login(String username, String password) {

        AdmissionEntity student =
                admissionRepo.findByUsername(username);

        if (student != null &&
                student.getPassword().equals(password)) {

            return student;
        }

        return null;
    }
}
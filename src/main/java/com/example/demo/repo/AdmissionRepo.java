package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AdmissionEntity;

@Repository
public interface AdmissionRepo extends JpaRepository<AdmissionEntity, Long> {

    // Login ke liye
    AdmissionEntity findByUsername(String username);
    
    

    // Duplicate username check
    boolean existsByUsername(String username);

    // Latest admission fetch (student dashboard ke liye)
    AdmissionEntity findTopByStudentIdOrderByIdDesc(Long studentId);
    
    AdmissionEntity findByStudentId(Long studentId);
    
}
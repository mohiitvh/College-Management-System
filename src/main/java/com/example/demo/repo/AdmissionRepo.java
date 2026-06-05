package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AdmissionEntity;

public interface AdmissionRepo
        extends JpaRepository<AdmissionEntity, Long>{

    AdmissionEntity findByUsername(String username);

}
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CertificateEntity;
import com.example.demo.repo.CertificateRepo;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepo repo;

    public CertificateEntity save(CertificateEntity c) {
        return repo.save(c);
    }

    public List<CertificateEntity> findAll() {
        return repo.findAll();
    }

    public List<CertificateEntity> findByStudent(Long id) {
        return repo.findByStudentId(id);
    }

    public CertificateEntity findById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
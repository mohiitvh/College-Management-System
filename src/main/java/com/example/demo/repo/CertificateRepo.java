package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.CertificateEntity;

public interface CertificateRepo
        extends JpaRepository<CertificateEntity, Long> {

    List<CertificateEntity> findByStudentId(Long studentId);

    boolean existsByStudentIdAndCertificateTypeAndStatus(
            Long studentId,
            String certificateType,
            String status);
    
    long countByStudentId(Long studentId);

    long countByStudentIdAndStatus(Long studentId, String status);
    
//    @Query("SELECT COUNT(c) FROM CertificateRequestEntity c WHERE c.status = :status")
//    long countByStatus(@Param("status") String status);
    
    long countByStatus(String status); 
    
}
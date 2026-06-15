package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PaymentEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentEntity, Long> {

    List<PaymentEntity> findByPaymentStatus(String paymentStatus);

    List<PaymentEntity> findByDueAmountGreaterThan(Double amount);

    List<PaymentEntity> findByStudentNameContainingIgnoreCase(String studentName);

    List<PaymentEntity> findByCourseNameContainingIgnoreCase(String courseName);

    List<PaymentEntity> findAllByStudentId(Long studentId);

    @Query("SELECT COALESCE(SUM(p.paidAmount),0) FROM PaymentEntity p")
    Double getTotalCollectedFee();

    @Query("SELECT COALESCE(SUM(p.dueAmount),0) FROM PaymentEntity p")
    Double getTotalDueFee();

    Long countByPaymentStatus(String paymentStatus);


}
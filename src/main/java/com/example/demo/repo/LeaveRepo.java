package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LeaveEntity;

public interface LeaveRepo extends JpaRepository<LeaveEntity, Long>{

    List<LeaveEntity> findByUserId(Long userId);

    List<LeaveEntity> findByRole(String role);

    List<LeaveEntity> findByRoleOrderByIdDesc(String role);

    long countByUserId(Long userId);

    long countByUserIdAndStatus(Long userId, String status);
    
    long countByStatus(String status);

    List<LeaveEntity> findByStatus(String status);

    List<LeaveEntity> findTop5ByOrderByAppliedDateDesc();
}
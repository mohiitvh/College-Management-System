package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LeaveEntity;
import com.example.demo.repo.LeaveRepo;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepo leaveRepo;

    public LeaveEntity save(LeaveEntity leave) {
        return leaveRepo.save(leave);
    }

    public List<LeaveEntity> getAllLeaves() {
        return leaveRepo.findAll();
    }

    public LeaveEntity getLeaveById(Long id) {
        return leaveRepo.findById(id).orElse(null);
    }

    public List<LeaveEntity> getLeavesByUserId(Long userId) {
        return leaveRepo.findByUserId(userId);
    }

    public void approveLeave(Long id) {

        LeaveEntity leave =
                leaveRepo.findById(id).orElse(null);

        if(leave != null) {

            leave.setStatus("Approved");

            leaveRepo.save(leave);
        }
    }

    public void rejectLeave(Long id) {

        LeaveEntity leave =
                leaveRepo.findById(id).orElse(null);

        if(leave != null) {

            leave.setStatus("Rejected");

            leaveRepo.save(leave);
        }
    }
    

    public long countByUserId(Long userId){
        return leaveRepo.countByUserId(userId);
    }

    public long countByUserIdAndStatus(
            Long userId,
            String status){

        return leaveRepo.countByUserIdAndStatus(
                userId,
                status);
    }
    public long getTotalLeaves(){
        return leaveRepo.count();
    }

    public long getPendingLeaves(){
        return leaveRepo.findAll()
                .stream()
                .filter(l -> "Pending".equals(l.getStatus()))
                .count();
    }

    public long getApprovedLeaves(){
        return leaveRepo.findAll()
                .stream()
                .filter(l -> "Approved".equals(l.getStatus()))
                .count();
    }

    public long getRejectedLeaves(){
        return leaveRepo.findAll()
                .stream()
                .filter(l -> "Rejected".equals(l.getStatus()))
                .count();
    }
    public List<LeaveEntity> getLeavesByRole(String role){
        return leaveRepo.findByRoleOrderByIdDesc(role);
    }
    public LeaveEntity findById(Long id) {
        return leaveRepo.findById(id).orElse(null);
    }
    
    public List<LeaveEntity> getRecentLeaves() {
        return leaveRepo.findTop5ByOrderByAppliedDateDesc();
    }
    
   
}
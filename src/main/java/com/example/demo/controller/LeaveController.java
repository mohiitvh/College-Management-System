package com.example.demo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.LeaveEntity;
import com.example.demo.entity.Staff;
import com.example.demo.entity.Student;
import com.example.demo.repo.CertificateRepo;
import com.example.demo.service.LeaveService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LeaveController {

    @Autowired
    private LeaveService leaveService;
    
    @Autowired
    private CertificateRepo certificateRepo;
    

    // ==========================
    // STUDENT APPLY LEAVE
    // ==========================

    @GetMapping("/student/apply-leave")
    public String applyLeavePage() {
        return "Student/apply-leave";
    }

    @PostMapping("/student/save-leave")
    public String saveStudentLeave(
            @ModelAttribute LeaveEntity leave,
            @RequestParam(value = "file", required = false) MultipartFile file,
            HttpSession session) throws IOException {

        Student student =
                (Student) session.getAttribute("loggedStudent");

        if (student == null) {
            return "redirect:/loginStudent";
        }

        leave.setUserId(student.getId());
        leave.setName(student.getName());
        leave.setRole("STUDENT");
        leave.setAppliedDate(LocalDate.now());
        leave.setStatus("Pending");

        long days = ChronoUnit.DAYS.between(
                leave.getFromDate(),
                leave.getToDate()) + 1;

        leave.setTotalDays((int) days);

        if (file != null && !file.isEmpty()) {
            leave.setAttachment(file.getBytes());
        }

        leaveService.save(leave);

        return "redirect:/student/leave-history";
    }

    @GetMapping("/student/leave-history")
    public String studentLeaveHistory(
            HttpSession session,
            Model model) {

        Student student =
                (Student) session.getAttribute("loggedStudent");

        if (student == null) {
            return "redirect:/loginStudent";
        }

        model.addAttribute(
                "leaves",
                leaveService.getLeavesByUserId(student.getId()));

        return "Student/leave-history";
    }

    @GetMapping("/student/leave-details/{id}")
    public String studentLeaveDetails(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "leave",
                leaveService.getLeaveById(id));

        return "Student/leave-details";
    }
    
    @GetMapping("/indexStudent")
    public String indexStudent(HttpSession session, Model model) {

        Student student =
                (Student) session.getAttribute("loggedStudent");

        if(student == null){
            return "redirect:/loginStudent";
        }

        model.addAttribute("student", student);

        Long studentId = student.getId();

        // Leave Count
        model.addAttribute(
                "totalLeaves",
                leaveService.countByUserId(studentId));

        model.addAttribute(
                "approvedLeaves",
                leaveService.countByUserIdAndStatus(
                        studentId,
                        "Approved"));

        model.addAttribute(
                "pendingLeaves",
                leaveService.countByUserIdAndStatus(
                        studentId,
                        "Pending"));

        // Certificate Count
        long totalCertificates =
                certificateRepo.findByStudentId(studentId).size();

        long approvedCertificates =
                certificateRepo.findByStudentId(studentId)
                        .stream()
                        .filter(c -> "APPROVED".equalsIgnoreCase(c.getStatus()))
                        .count();

        model.addAttribute(
                "certificateCount",
                totalCertificates);

        model.addAttribute(
                "approvedCertificateCount",
                approvedCertificates);

        return "index/indexStudent";
    }
    
    // ==========================
    // STAFF APPLY LEAVE
    // ==========================

    @GetMapping("/staff/apply-leave")
public String staffApplyLeavePage() {
    return "Staff/apply-leave";
}

@PostMapping("/staff/save-leave")
public String saveStaffLeave(
        @ModelAttribute LeaveEntity leave,
        @RequestParam(value = "file", required = false) MultipartFile file,
        HttpSession session) throws IOException {

    Staff staff =
            (Staff) session.getAttribute("loggedStaff");

    if (staff == null) {
        return "redirect:/loginStaff";
    }

    leave.setUserId(staff.getId());
    leave.setName(staff.getName());
    leave.setRole("STAFF");
    leave.setAppliedDate(LocalDate.now());
    leave.setStatus("Pending");

    long days = ChronoUnit.DAYS.between(
            leave.getFromDate(),
            leave.getToDate()) + 1;

    leave.setTotalDays((int) days);

    if (file != null && !file.isEmpty()) {
        leave.setAttachment(file.getBytes());
    }

    leaveService.save(leave);

    return "redirect:/staff/leave-history";
}

@GetMapping("/staff/leave-history")
public String staffLeaveHistory(
        HttpSession session,
        Model model) {

    Staff staff =
            (Staff) session.getAttribute("loggedStaff");

    if (staff == null) {
        return "redirect:/loginStaff";
    }

    model.addAttribute(
            "leaves",
            leaveService.getLeavesByUserId(staff.getId()));

    return "Staff/leave-history";
}

@GetMapping("/staff/leave-details/{id}")
public String leaveDetails(
        @PathVariable Long id,
        Model model,
        HttpSession session) {

    Staff staff =
            (Staff) session.getAttribute("loggedStaff");

    if(staff == null){
        return "redirect:/loginStaff";
    }

    LeaveEntity leave =
            leaveService.findById(id);

    model.addAttribute("leave", leave);

    return "Staff/leave-details";
}

@GetMapping("/indexStaff")
public String indexStaff(HttpSession session,
                         Model model) {

    Staff staff =
            (Staff) session.getAttribute("loggedStaff");

    if(staff == null){
        return "redirect:/loginStaff";
    }

    model.addAttribute("staff", staff);

    Long staffId = staff.getId();

    model.addAttribute(
            "totalLeaves",
            leaveService.countByUserId(staffId));

    model.addAttribute(
            "approvedLeaves",
            leaveService.countByUserIdAndStatus(
                    staffId,
                    "Approved"));

    model.addAttribute(
            "pendingLeaves",
            leaveService.countByUserIdAndStatus(
                    staffId,
                    "Pending"));

    // NEW
    model.addAttribute(
            "leaves",
            leaveService.getLeavesByUserId(staffId));

    return "index/indexStaff";
}

    
    
//==========================
//ADMIN
//==========================

@GetMapping("/allLeaves")
public String allLeaves(Model model) {

 model.addAttribute("studentLeaves",
         leaveService.getLeavesByRole("STUDENT"));

 model.addAttribute("staffLeaves",
         leaveService.getLeavesByRole("STAFF"));

 return "Admin/all-leaves";
}

@GetMapping("/leave-management")
public String leaveManagement(Model model) {

    model.addAttribute("totalLeaves",
            leaveService.getTotalLeaves());

    model.addAttribute("pendingLeaves",
            leaveService.getPendingLeaves());

    model.addAttribute("approvedLeaves",
            leaveService.getApprovedLeaves());

    model.addAttribute("rejectedLeaves",
            leaveService.getRejectedLeaves());

    model.addAttribute("recentLeaves",
            leaveService.getRecentLeaves());

    return "Admin/leave-management";
}

@GetMapping("/admin/viewLeave/{id}")
public String viewLeave(
     @PathVariable Long id,
     Model model) {

 model.addAttribute(
         "leave",
         leaveService.getLeaveById(id));

 return "Admin/view-leave";
}

@GetMapping("/admin/leave/approve/{id}")
public String approveLeave(@PathVariable Long id){

 leaveService.approveLeave(id);

 return "redirect:/allLeaves";
}

@GetMapping("/admin/leave/reject/{id}")
public String rejectLeave(@PathVariable Long id){

 leaveService.rejectLeave(id);

 return "redirect:/allLeaves";
}
}
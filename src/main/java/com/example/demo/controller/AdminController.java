package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Admin;
import com.example.demo.entity.AdmissionEntity;
import com.example.demo.repo.AdmissionRepo;
import com.example.demo.repo.CertificateRepo;
import com.example.demo.repo.CourseRepo;
import com.example.demo.repo.PaymentRepo;
import com.example.demo.repo.StaffRepo;
import com.example.demo.repo.StudentRepo;
import com.example.demo.service.AdminService;
import com.example.demo.service.LeaveService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.StaffService;
import com.example.demo.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private AdmissionRepo admissionRepo;
    
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private PaymentRepo paymentRepo;
    
    @Autowired
    private CertificateRepo certificateRepo;
    
    @Autowired
    private CourseRepo courseRepo;

    // ---------------- LOGIN ----------------

    @GetMapping("/loginAdmin")
    public String loginAdminPage() {
        return "login_signup/loginAdmin";
    }

    @GetMapping("/signupAdmin")
    public String signupAdminPage() {
        return "login_signup/signupAdmin";
    }

    @PostMapping("/signupAdmin")
    public String signupAdmin(@ModelAttribute Admin admin,
                              @RequestParam("photo") org.springframework.web.multipart.MultipartFile photo,
                              Model model) throws IOException {

        if (!photo.isEmpty()) {
            admin.setAdminPhoto(photo.getBytes());
        }

        String result = adminService.register(admin);

        if ("INVALID_ADMIN_PASS".equals(result)) {
            model.addAttribute("error", "Invalid Admin Secret Key");
            return "login_signup/signupAdmin";
        }

        return "redirect:/loginAdmin";
    }

    
    @PostMapping("/loginAdmin")
    public String loginAdmin(@RequestParam String email,
                             @RequestParam String password,
                             HttpSession session) {

        Admin admin = adminService.login(email, password);

        if (admin != null) {
            session.setAttribute("loggedAdmin", admin);
            return "redirect:/indexAdmin";
        }

        return "redirect:/loginAdmin";
    }
    @Autowired
    private LeaveService leaveService;
    
    // ---------------- DASHBOARD ----------------

    @GetMapping("/indexAdmin")
    public String indexAdmin(HttpSession session, Model model) {

        Admin admin = (Admin) session.getAttribute("loggedAdmin");

        if (admin == null) {
            return "redirect:/loginAdmin";
        }

        model.addAttribute("admin", admin);

        model.addAttribute("totalStudents", studentRepo.count());
        model.addAttribute("totalStaff", staffRepo.count());
        model.addAttribute("totalAdmissions", admissionRepo.count());

        model.addAttribute("feeCollected",
                paymentRepo.getTotalCollectedFee());

        model.addAttribute("totalLeaves",
                leaveService.getTotalLeaves());

        model.addAttribute("pendingLeaves",
                leaveService.getPendingLeaves());

        model.addAttribute("approvedLeaves",
                leaveService.getApprovedLeaves());

        model.addAttribute("rejectedLeaves",
                leaveService.getRejectedLeaves());

        model.addAttribute("totalCertificates",
                certificateRepo.count());

        model.addAttribute("approvedCertificates",
                certificateRepo.countByStatus("APPROVED"));

        model.addAttribute("totalCourses",
                courseRepo.count());

        model.addAttribute("pendingPayments",
                paymentService.getPendingPaymentsCount());

        model.addAttribute("leaves",
                leaveService.getAllLeaves()
                            .stream()
                            .limit(5)
                            .toList());

        return "index/indexAdmin";
    }
    // ---------------- PHOTO ----------------

    @GetMapping("/admin/photo/{id}")
    public ResponseEntity<byte[]> getAdminPhoto(@PathVariable Long id) {

        Admin admin = adminService.findById(id);

        if (admin == null || admin.getAdminPhoto() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(admin.getAdminPhoto());
    }

    // ---------------- LOGOUT ----------------

    @GetMapping("/logoutAdmin")
    public String logoutAdmin(HttpSession session) {
        session.invalidate();
        return "redirect:/loginAdmin";
    }

   
    // ---------------- FEE PAGES ----------------

    @GetMapping("/fee-management")
    public String feeManagement() {
        return "Admin/feeManagement";
    }

    @GetMapping("/admin-payments")
    public String adminPayments() {
        return "Admin/admin-payment";
    }
    
 // ================= STUDENTS =================

    @GetMapping("/admin/students")
    public String allStudents(Model model) {

        model.addAttribute("students", studentService.findAll());
        return "Admin/allStudents";
    }

    // ================= STAFF =================

    @GetMapping("/admin/staff")
    public String allStaff(Model model) {

        model.addAttribute("staffList", staffService.findAll());
        return "Admin/allStaff";
    }

 // ================= ADMISSIONS =================

    @GetMapping("/admin/admissions")
    public String allAdmissions(Model model) {
        model.addAttribute("admissions", admissionRepo.findAll());
        return "Admin/allAdmissions";
    }
 // ================= ADD STAFF =================

    @GetMapping("/addStaff")
    public String addStaffPage() {
        return "Admin/addStaff";
    }


    // ================= ATTENDANCE MANAGEMENT =================

    @GetMapping("/attendance-management")
    public String attendanceManagement() {
        return "Admin/attendanceManagement";
    }

    // ================= RESULT MANAGEMENT =================

    @GetMapping("/result-management")
    public String resultManagement() {
        return "Admin/resultManagement";
    }

    
    @GetMapping("/reports")
    public String reports() {
        return "Admin/reports";
    }
   
}
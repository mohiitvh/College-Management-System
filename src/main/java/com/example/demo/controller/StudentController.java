package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {
	
	@Autowired
    private StudentService studentService;
    
    @GetMapping("/loginStudent")
    public String loginStudentPage() {
        return "login_signup/loginStudent";
    }

    @GetMapping("/signupStudent")
    public String signupStudentPage() {
        return "login_signup/signupStudent";
    }
    
    @GetMapping("/student/photo/{id}")
    public ResponseEntity<byte[]> getStudentPhoto(@PathVariable Long id){

        Student student = studentService.findById(id);

        if(student == null || student.getStudentPhoto() == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(student.getStudentPhoto());
    }
    
    @PostMapping("/signupStudent")
    public String signupStudent(
            @ModelAttribute Student student,
            @RequestParam("photo") MultipartFile photo)
            throws IOException {

        if (!photo.isEmpty()) {
            student.setStudentPhoto(photo.getBytes());
        }

        studentService.save(student);

        return "redirect:/loginStudent";
    }
    
    @PostMapping("/loginStudent")
    public String loginStudent(@RequestParam String email,
            @RequestParam String password,
            HttpSession session) {

        Student student = studentService.login(email, password);

        if (student != null) {

            session.setAttribute("loggedStudent", student);

            return "redirect:/indexStudent";
        }

        return "redirect:/loginStudent";
    }

    
    // id Card
    @GetMapping("/idStudent")
    public String idStudent(HttpSession session, Model model) {

        Student student = (Student) session.getAttribute("loggedStudent");

        if (student == null) {
            return "redirect:/loginStudent";
        }

        model.addAttribute("student", student);

        return "Student/idStudent";
    }



    @GetMapping("/student/profile")
    public String viewProfile(HttpSession session, Model model) {

        Student student =
                (Student) session.getAttribute("loggedStudent");

        if(student == null){
            return "redirect:/loginStudent";
        }

        model.addAttribute("student", student);

        return "Student/viewProfileStudent";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate(); // clear session

        return "redirect:/"; // home page
    }

    
    @GetMapping("/student/payment")
    public String feePayment(HttpSession session, Model model) {

        Student student =
                (Student) session.getAttribute("loggedStudent");

        if(student == null){
            return "redirect:/loginStudent";
        }

        model.addAttribute("student", student);

        return "Student/feePayment";
    }
        @GetMapping("/fee-history")
        public String feeHistory() {
            return "Student/feeHistory";
        }

        @GetMapping("/fee-details")
        public String feeDetails() {
            return "Student/feeDetails";
        }
    
}

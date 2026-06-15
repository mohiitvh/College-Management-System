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

import com.example.demo.entity.Staff;
import com.example.demo.service.StaffService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StaffController {
	
	  @Autowired
	    private StaffService staffService;
	    
	    @GetMapping("/loginStaff")
	    public String loginStaffPage() {
	        return "login_signup/loginStaff";
	    }
	    
	    @GetMapping("/signupStaff")
	    public String signupStaffPage() {
	        return "login_signup/signupStaff";
	    }
	    
	    

	    @PostMapping("/signupStaff")
	    public String signupStaff(
	            @ModelAttribute Staff staff,
	            @RequestParam("photo") MultipartFile photo)
	            throws IOException {

	        if(!photo.isEmpty()){
	            staff.setStaffPhoto(photo.getBytes());
	        }

	        staffService.save(staff);

	        return "redirect:/loginStaff";
	    }
	    
	    @PostMapping("/loginStaff")
	    public String loginStaff(
	            @RequestParam String email,
	            @RequestParam String password,
	            HttpSession session) {

	        Staff staff = staffService.login(email, password);

	        if (staff != null) {

	            session.setAttribute("loggedStaff", staff);

	            return "redirect:/indexStaff";
	        }

	        return "redirect:/loginStaff";
	    }
	    
	    @GetMapping("/staff/photo/{id}")
	    public ResponseEntity<byte[]> getStaffPhoto(
	            @PathVariable Long id){

	        Staff staff = staffService.findById(id);

	        if(staff == null || staff.getStaffPhoto() == null){
	            return ResponseEntity.notFound().build();
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.IMAGE_JPEG)
	                .body(staff.getStaffPhoto());
	    }
	    
	    @GetMapping("/staff/profile")
	    public String viewStaffProfile(
	            HttpSession session,
	            Model model) {

	        Staff staff =
	                (Staff) session.getAttribute("loggedStaff");

	        if(staff == null){
	            return "redirect:/loginStaff";
	        }

	        model.addAttribute("staff", staff);

	        return "Staff/viewProfileStaff";
	    }
	    
	 //========== Id Card
	    @GetMapping("/idStaff")
	    public String idStaff(HttpSession session, Model model) {

	        Staff staff = (Staff) session.getAttribute("loggedStaff");

	        if (staff == null) {
	            return "redirect:/loginStaff";
	        }

	        model.addAttribute("staff", staff);

	        return "Staff/idStaff";
	    }

	    
	    
	    @GetMapping("/logoutStaff")
	    public String logoutStaff(HttpSession session){

	        session.invalidate();

	        return "redirect:/loginStaff";
	    }

}

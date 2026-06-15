package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Admin;
import com.example.demo.entity.AdmissionEntity;
import com.example.demo.entity.CourseEntity;
import com.example.demo.entity.Staff;
import com.example.demo.entity.Student;
import com.example.demo.repo.AdmissionRepo;
import com.example.demo.repo.CourseRepo;
import com.example.demo.repo.StaffRepo;
import com.example.demo.repo.StudentRepo;
import com.example.demo.service.AdminService;
import com.example.demo.service.AdmissionService;
import com.example.demo.service.StaffService;
import com.example.demo.service.StudentService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.swing.text.Document;
import org.springframework.ui.Model;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	// ================= HOME =================
	 @Autowired
	    private StudentRepo studentRepo;

	    @Autowired
	    private StaffRepo staffRepo;

	    @Autowired
	    private CourseRepo courseRepo;

	    @GetMapping("/")
	    public String home(Model model) {

	        model.addAttribute(
	                "totalStudents",
	                studentRepo.count());

	        model.addAttribute(
	                "totalCourses",
	                courseRepo.count());

	        model.addAttribute(
	                "totalStaff",
	                staffRepo.count());

	        return "master";
	    }

	// ================= ABOUT =================
	@GetMapping("/about-college")
	public String aboutCollege() {
		return "about/about-college";
	}

	@GetMapping("/vision-mission")
	public String visionMission() {
		return "about/vision-mission";
	}

	@GetMapping("/faculties")
	public String faculties() {
		return "about/faculties";
	}

	@GetMapping("/academic-calendar")
	public String academicCalendar() {
		return "about/academic-calendar";
	}

	@GetMapping("/campus-layout")
	public String campusLayout() {
		return "about/campus-layout";
	}

	@GetMapping("/student-support")
	public String studentSupport() {
		return "about/student-support";
	}

	@GetMapping("/internship")
	public String internship() {
		return "about/internship";
	}

	// ================= ADMINISTRATION =================
	@GetMapping("/teaching-staff")
	public String teachingStaff() {
		return "administration/teaching-staff";
	}

	@GetMapping("/non-teaching-staff")
	public String nonTeachingStaff() {
		return "administration/non-teaching-staff";
	}

	@GetMapping("/counselling-cell")
	public String counsellingCell() {
		return "administration/counselling-cell";
	}

	@GetMapping("/seminar-workshop")
	public String seminarWorkshop() {
		return "administration/seminar-workshop";
	}

	@GetMapping("/placement-cell")
	public String placementCell() {
		return "administration/placement-cell";
	}

	// ================= ACADEMICS =================
	@GetMapping("/department")
	public String department() {
		return "academics/department";
	}

	@GetMapping("/intake-seat")
	public String intakeSeat() {
		return "academics/intake-seat";
	}

	@GetMapping("/course-offer")
	public String courseOffer() {
		return "academics/course-offer";
	}

	@GetMapping("/academic-calendar-2")
	public String academicCalendar2() {
		return "academics/academic-calendar";
	}

	// ================= ADMISSION =================
	@GetMapping("/apply-admission")
	public String applyAdmission() {
		return "admission/apply-admission";
	}

	@GetMapping("/guideline")
	public String guideline() {
		return "admission/guideline";
	}

	@GetMapping("/registration-process")
	public String registrationProcess() {
		return "admission/registration-process";
	}

	@GetMapping("/eligibility-criteria")
	public String eligibilityCriteria() {
		return "admission/eligibility-criteria";
	}

	@GetMapping("/fee-structure")
	public String feeStructure() {
		return "admission/fee-structure";
	}

// ================= FACILITIES =================
	@GetMapping("/facilities/library")
	public String library() {
		return "facilities/library";
	}

	@GetMapping("/facilities/lab")
	public String lab() {
		return "facilities/lab";
	}

	@GetMapping("/facilities/electricity")
	public String electricity() {
		return "facilities/electricity";
	}

	@GetMapping("/facilities/smart-class")
	public String smartClass() {
		return "facilities/smart-class";
	}

	@GetMapping("/facilities/guest-house")
	public String guestHouse() {
		return "facilities/guest-house";
	}

	@GetMapping("/facilities/hostel")
	public String hostel() {
		return "facilities/hostel";
	}

	@GetMapping("/facilities/wifi")
	public String wifi() {
		return "facilities/wifi";
	}

	@GetMapping("/facilities/parking")
	public String parking() {
		return "facilities/parking";
	}

	@GetMapping("/facilities/health")
	public String health() {
		return "facilities/health";
	}

	@GetMapping("/facilities/boys-room")
	public String boys() {
		return "facilities/boys-room";
	}

	@GetMapping("/facilities/girls-room")
	public String girls() {
		return "facilities/girls-room";
	}

	@GetMapping("/facilities/cafeteria")
	public String cafeteria() {
		return "facilities/cafeteria";
	}

	@GetMapping("/facilities/seminar")
	public String seminar() {
		return "facilities/seminar";
	}

// ================= EXAMINATION =================
	@GetMapping("/marksheet")
	public String marksheet() {
		return "examination/marksheet";
	}

	@GetMapping("/exam-notice")
	public String examNotice() {
		return "examination/exam-notice";
	}

	@GetMapping("/supporting-staff")
	public String supportingStaff() {
		return "examination/supporting-staff";
	}

// ================= CONTACT =================
	@GetMapping("/principal")
	public String principal() {
		return "contact/principal";
	}

	@GetMapping("/college-map")
	public String collegeMap() {
		return "contact/college-map";
	}

	@GetMapping("/phone")
	public String phone() {
		return "contact/phone";
	}

	@GetMapping("/email")
	public String email() {
		return "contact/email";
	}

// ===== UG COURSES =====
	@GetMapping("/course/btech")
	public String btechPage() {
		return "course/btech";
	}

	@GetMapping("/course/bca")
	public String bcaPage() {
		return "course/bca";
	}

	@GetMapping("/course/bba")
	public String bbaPage() {
		return "course/bba";
	}

	@GetMapping("/course/bsc")
	public String bscPage() {
		return "course/bsc";
	}

	@GetMapping("/course/ba")
	public String baPage() {
		return "course/ba";
	}

	@GetMapping("/course/bcom")
	public String bcomPage() {
		return "course/bcom";
	}

	@GetMapping("/course/diploma")
	public String diplomaPage() {
		return "course/diploma";
	}

// ===== PG COURSES =====
	@GetMapping("/course/ma")
	public String maPage() {
		return "course/ma";
	}

	@GetMapping("/course/msc")
	public String mscPage() {
		return "course/msc";
	}

	@GetMapping("/course/mca")
	public String mcaPage() {
		return "course/mca";
	}

	@GetMapping("/course/mtech")
	public String mtechPage() {
		return "course/mtech";
	}

	@GetMapping("/course/mcom")
	public String mcomPage() {
		return "course/mcom";
	}

	@GetMapping("/course/mba")
	public String mbaPage() {
		return "course/mba";
	}

// ===== important link
	@GetMapping("/result")
	public String resultPage() {
		return "result";
	}

	@PostMapping("/searchResult")
	public String searchResult(@RequestParam String programType, @RequestParam String course,
			@RequestParam String rollNo, @RequestParam String registrationNo, Model model) {

		model.addAttribute("programType", programType);
		model.addAttribute("course", course);
		model.addAttribute("rollNo", rollNo);
		model.addAttribute("registrationNo", registrationNo);

		return "result";
	}

	@GetMapping("/scholarship")
	public String scholarshipPage() {
		return "scholarship";
	}

	@GetMapping("/examination")
	public String examinationPage(HttpSession session) {

		Student student = (Student) session.getAttribute("loggedStudent");

		if (student == null) {
			return "redirect:/loginStudent";
		}

		return "examination";
	}
	
	
}

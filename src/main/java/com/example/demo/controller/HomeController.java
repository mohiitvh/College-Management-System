package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Admin;
import com.example.demo.entity.AdmissionEntity;
import com.example.demo.entity.Staff;
import com.example.demo.entity.Student;
import com.example.demo.repo.AdmissionRepo;
import com.example.demo.service.AdminService;
import com.example.demo.service.AdmissionService;
import com.example.demo.service.StaffService;
import com.example.demo.service.StudentService;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@Controller
public class HomeController {

    // ================= HOME =================
    @GetMapping("/")
    public String home() {
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
    public String searchResult(
            @RequestParam String programType,
            @RequestParam String course,
            @RequestParam String rollNo,
            @RequestParam String registrationNo,
            Model model) {

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

    // -------------- Login--------------//
    @Autowired
    private AdminService adminService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private StudentService studentService;

    // ================= LOGIN PAGES =================

    @GetMapping("/loginAdmin")
    public String loginAdminPage() {
        return "login_signup/loginAdmin";
    }

    @GetMapping("/loginStaff")
    public String loginStaffPage() {
        return "login_signup/loginStaff";
    }

    @GetMapping("/loginStudent")
    public String loginStudentPage() {
        return "login_signup/loginStudent";
    }

    // ================= SIGNUP PAGES =================
    @GetMapping("/signupAdmin")
    public String signupAdminPage() {
        return "login_signup/signupAdmin";
    }

    @GetMapping("/signupStaff")
    public String signupStaffPage() {
        return "login_signup/signupStaff";
    }

    @GetMapping("/signupStudent")
    public String signupStudentPage() {
        return "login_signup/signupStudent";
    }

    // ================= SIGNUP SAVE =================

    @PostMapping("/signupAdmin")
    public String signupAdmin(@ModelAttribute Admin admin) {

        adminService.save(admin);

        return "redirect:/loginAdmin";
    }

    @PostMapping("/signupStaff")
    public String signupStaff(@ModelAttribute Staff staff) {

        staffService.save(staff);

        return "redirect:/loginStaff";
    }

    @PostMapping("/signupStudent")
    public String signupStudent(@ModelAttribute Student student) {

        studentService.save(student);

        return "redirect:/loginStudent";
    }

    // ================= ADMIN LOGIN =================
    @PostMapping("/loginAdmin")
    public String loginAdmin(@RequestParam String email,
            @RequestParam String password) {

        Admin admin = adminService.login(email, password);

        if (admin != null) {
            return "redirect:/indexAdmin";
        }

        return "redirect:/loginAdmin";
    }

    @GetMapping("/indexAdmin")
    public String indexAdmin() {
        return "index/indexAdmin";
    }

    // ================= STAFF LOGIN =================
    @PostMapping("/loginStaff")
    public String loginStaff(@RequestParam String email,
            @RequestParam String password,
            HttpSession session) {

        Staff staff = staffService.login(email, password);

        if (staff != null) {

            session.setAttribute("loggedStaff", staff);

            return "redirect:/indexStaff";
        }

        return "redirect:/loginStaff";
    }

    // id
    @GetMapping("/idStaff")
    public String idStaff(HttpSession session, Model model) {

        Staff staff = (Staff) session.getAttribute("loggedStaff");

        if (staff == null) {
            return "redirect:/loginStaff";
        }

        model.addAttribute("staff", staff);

        return "Staff/idStaff";
    }

    @GetMapping("/indexStaff")
    public String indexStaff() {
        return "index/indexStaff";
    }

    // ================= STUDENT LOGIN =================
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

    // id
    @GetMapping("/idStudent")
    public String idStudent(HttpSession session, Model model) {

        Student student = (Student) session.getAttribute("loggedStudent");

        if (student == null) {
            return "redirect:/loginStudent";
        }

        model.addAttribute("student", student);

        return "Student/idStudent";
    }
    @PostMapping("/saveAdmission")
    public String saveAdmission(@ModelAttribute AdmissionEntity admission) {

        admissionService.save(admission);

        return "redirect:/indexStudent";
    }
//    @GetMapping("/indexStudent")
//    public String indexStudent() {
//        return "index/indexStudent";
//    }
    @GetMapping("/indexStudent")
    public String indexStudent(HttpSession session,
                               Model model) {

        AdmissionEntity student =
                (AdmissionEntity) session.getAttribute("student");

        if(student == null){
            return "redirect:/admission";
        }

        model.addAttribute("student", student);

        return "index/indexStudent";
    }
    @GetMapping("/student/profile")
    public String viewProfile(HttpSession session, Model model) {

        AdmissionEntity student =
                (AdmissionEntity) session.getAttribute("student");

        if (student == null) {
            return "redirect:/admission";
        }

        model.addAttribute("student", student);

        return "viewProfileStudent";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate(); // clear session

        return "redirect:/"; // home page
    }

    // ---------------------- Admission
    @Autowired
    private AdmissionService admissionService;

    @GetMapping("/admission")
    public String admissionPage() {
        return "admission";
    }

    @GetMapping("/AdmissionSuccessful")
    public String successPage() {
        return "AdmissionSuccessful";
    }

    @PostMapping("/admission/save")
    public String saveAdmission(

            @ModelAttribute AdmissionEntity admission,

            @RequestParam("passportPhotoFile") MultipartFile passportPhoto,
            @RequestParam("signatureFile") MultipartFile signature,
            @RequestParam("aadhaarCardFile") MultipartFile aadhaarCard,
            @RequestParam("class10MarksheetFile") MultipartFile class10Marksheet,
            @RequestParam("class12MarksheetFile") MultipartFile class12Marksheet,
            @RequestParam("migrationCertificateFile") MultipartFile migrationCertificate,
            @RequestParam("categoryCertificateFile") MultipartFile categoryCertificate,
            @RequestParam("marksheetUploadFile") MultipartFile marksheetUpload

    ) throws IOException {

        if (!passportPhoto.isEmpty()) {
            admission.setPassportPhoto(passportPhoto.getBytes());
        }

        if (!signature.isEmpty()) {
            admission.setSignature(signature.getBytes());
        }

        if (!aadhaarCard.isEmpty()) {
            admission.setAadhaarCard(aadhaarCard.getBytes());
        }

        if (!class10Marksheet.isEmpty()) {
            admission.setClass10Marksheet(class10Marksheet.getBytes());
        }

        if (!class12Marksheet.isEmpty()) {
            admission.setClass12Marksheet(class12Marksheet.getBytes());
        }

        if (!migrationCertificate.isEmpty()) {
            admission.setMigrationCertificate(
                    migrationCertificate.getBytes());
        }

        if (!categoryCertificate.isEmpty()) {
            admission.setCategoryCertificate(
                    categoryCertificate.getBytes());
        }

        if (!marksheetUpload.isEmpty()) {
            admission.setMarksheetUpload(
                    marksheetUpload.getBytes());
        }

        admissionService.save(admission);

        return "redirect:/indexStudent";
    }
    
//    @GetMapping("/student/dashboard")
//    public String dashboard(HttpSession session, Model model) {
//
//        AdmissionEntity student =
//                (AdmissionEntity) session.getAttribute("student");
//
//        if (student == null) {
//            return "redirect:/admission";
//        }
//
//        model.addAttribute("student", student);
//
//        return "indexStudent";
//    }

    
    
    
    
    
    @PostMapping("/admission/login")
    public String loginAdmission(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session) {

        AdmissionEntity student =
                admissionService.login(username, password);

        if(student != null){

            session.setAttribute("student", student);

            return "redirect:/indexStudent";
        }

        return "redirect:/admission";
    }
    

}

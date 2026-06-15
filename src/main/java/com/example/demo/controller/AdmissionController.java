package com.example.demo.controller;
import com.example.demo.service.PaymentService;
import org.thymeleaf.context.Context;
import java.io.OutputStream;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.demo.entity.AdmissionEntity;
import com.example.demo.entity.CourseEntity;
import com.example.demo.entity.Student;
import com.example.demo.repo.AdmissionRepo;
import com.example.demo.repo.CourseRepo;
import com.example.demo.repo.PaymentRepo;
import com.example.demo.service.AdmissionService;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class AdmissionController {

	
	
	@Autowired
    private AdmissionService admissionService;
    
	@Autowired
	private PaymentService paymentService;
	
    @Autowired
    private AdmissionRepo admissionRepo;

    @Autowired
    private CourseRepo courseRepository;
    
    @Autowired
    private SpringTemplateEngine templateEngine;

    @GetMapping("/admission")
    public String admissionForm(HttpSession session,
                                Model model) {

        Student student =
                (Student) session.getAttribute("loggedStudent");

        if(student==null){
            return "redirect:/loginStudent";
        }

        AdmissionEntity old =
                admissionRepo.findByStudentId(student.getId());

        if(old!=null){
            model.addAttribute("msg",
                    "You already filled admission form.");

            model.addAttribute("admission",old);

            return "alreadyAdmission";
        }

        model.addAttribute("student",student);

        model.addAttribute(
                "courses",
                courseRepository.findAll());

        return "admission";
    }
    @PostMapping("/admission/save")
    public String saveAdmission(

            @ModelAttribute AdmissionEntity admission,

            @RequestParam Long courseId,

            @RequestParam("passportPhotoFile") MultipartFile passportPhoto,
            @RequestParam("signatureFile") MultipartFile signature,
            @RequestParam("aadhaarCardFile") MultipartFile aadhaarCard,
            @RequestParam("class10MarksheetFile") MultipartFile class10Marksheet,
            @RequestParam("class12MarksheetFile") MultipartFile class12Marksheet,
            @RequestParam("migrationCertificateFile") MultipartFile migrationCertificate,
            @RequestParam("categoryCertificateFile") MultipartFile categoryCertificate,
            @RequestParam("marksheetUploadFile") MultipartFile marksheetUpload,

            HttpSession session,
            Model model
    ) throws IOException {
    	Student student =
    	        (Student) session.getAttribute("loggedStudent");

    	if(student == null){
    	    return "redirect:/loginStudent";
    	}
    	
    	if(admissionRepo.existsByUsername(admission.getUsername())) {

    	    model.addAttribute("error",
    	            "Username already exists. Please use another username.");

    	    model.addAttribute("courses",
    	            courseRepository.findAll());

    	    return "admission";
    	}

    	admission.setStudentId(student.getId());
    	CourseEntity course =
    	        courseRepository.findById(courseId)
    	        .orElse(null);

    	admission.setCourse(course);

        admission.setStatus("Pending");

        // files save
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
            admission.setMigrationCertificate(migrationCertificate.getBytes());
        }

        if (!categoryCertificate.isEmpty()) {
            admission.setCategoryCertificate(categoryCertificate.getBytes());
        }

        if (!marksheetUpload.isEmpty()) {
            admission.setMarksheetUpload(marksheetUpload.getBytes());
        }

     // Save Admission
        AdmissionEntity savedAdmission =
                admissionService.save(admission);

        // Auto Create Payment Record
        paymentService.createPaymentFromAdmission(
                savedAdmission);

        // Open Admission View
        return "redirect:/admissionView/"
                + savedAdmission.getId();
    }
        

    @GetMapping("/admissionView/{id}")
    public String admissionView(
            @PathVariable Long id,
            Model model) {

        AdmissionEntity admission =
                admissionRepo.findById(id).orElse(null);

        if (admission == null) {
            return "redirect:/indexStudent";
        }

        model.addAttribute("admission", admission);

        return "admissionView";
    }
    
    @GetMapping("/admin/admission/approve/{id}")
    public String approveAdmission(@PathVariable Long id) {

        AdmissionEntity admission =
                admissionRepo.findById(id).orElse(null);

        if(admission != null) {

            admission.setStatus("Approved");

            admissionRepo.save(admission);
        }

        return "redirect:/admin/allAdmissions";
    }
    
    @GetMapping("/admin/admission/reject/{id}")
    public String rejectAdmission(@PathVariable Long id) {

        AdmissionEntity admission =
                admissionRepo.findById(id).orElse(null);

        if(admission != null) {

            admission.setStatus("Rejected");

            admissionRepo.save(admission);
        }

        return "redirect:/admin/allAdmissions";
    }
    
    @GetMapping("/admin/allAdmissions")
    public String allAdmissions(Model model) {

        List<AdmissionEntity> list = admissionRepo.findAll();

        model.addAttribute("admissions", list);

        return "Admin/allAdmissions";
    }
    
    @GetMapping("/admin/admission/view/{id}")
    public String viewAdmissionAR(
            @PathVariable Long id,
            Model model) {

        AdmissionEntity admission =
                admissionRepo.findById(id).orElse(null);

        if(admission == null){
            return "redirect:/admin/allAdmissions";
        }

        model.addAttribute("admission", admission);

        return "Admin/viewAdminAdmission-A-R";
    }
 
    
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
    
    //============viewAdmissionForm.html 
    @GetMapping("/viewAdmissionForm")
    public String viewAdmissionForm(
            HttpSession session,
            Model model) {

        Student student =
                (Student) session.getAttribute("loggedStudent");

        if(student == null){
            return "redirect:/loginStudent";
        }

        AdmissionEntity admission =
                admissionRepo.findTopByStudentIdOrderByIdDesc(
                        student.getId());

        if(admission == null){

            return "Student/admissionRequired";
        }

        model.addAttribute("admission", admission);

        return "Student/viewAdmissionForm";
    }
    
    
//=============Admission Form Download Pdf/Photo
    @GetMapping("/admission/photo/{id}")
    @ResponseBody
    public byte[] getPhoto(@PathVariable Long id) {

        AdmissionEntity admission =
                admissionRepo.findById(id).orElse(null);

        if(admission == null || admission.getPassportPhoto() == null) {
            return null;
        }

        return admission.getPassportPhoto();
    }
    
    @GetMapping("/admission/signature/{id}")
public ResponseEntity<byte[]> getSignature(
        @PathVariable Long id){

    AdmissionEntity admission =
            admissionRepo.findById(id).orElse(null);

    if(admission == null || admission.getSignature() == null){
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_PNG)
            .body(admission.getSignature());
}
    
@GetMapping("/admission/document/aadhaar/{id}")
public ResponseEntity<byte[]> aadhaar(@PathVariable Long id){

    AdmissionEntity a = admissionRepo.findById(id).orElse(null);

    if(a == null || a.getAadhaarCard() == null){
        return ResponseEntity.notFound().build();
    }

    System.out.println(a.getAadhaarCard().length);

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=Aadhaar.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(a.getAadhaarCard());
}

@GetMapping("/admission/document/class10/{id}")
public ResponseEntity<byte[]> class10(@PathVariable Long id){
    AdmissionEntity a = admissionRepo.findById(id).orElse(null);

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=Class10.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(a.getClass10Marksheet());
}

@GetMapping("/admission/document/class12/{id}")
public ResponseEntity<byte[]> class12(@PathVariable Long id){
    AdmissionEntity a = admissionRepo.findById(id).orElse(null);

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=Class12.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(a.getClass12Marksheet());
}

@GetMapping("/admission/document/migration/{id}")
public ResponseEntity<byte[]> migration(@PathVariable Long id){
    AdmissionEntity a = admissionRepo.findById(id).orElse(null);

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=Migration.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(a.getMigrationCertificate());
}

@GetMapping("/admission/document/category/{id}")
public ResponseEntity<byte[]> category(@PathVariable Long id){
    AdmissionEntity a = admissionRepo.findById(id).orElse(null);

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=Category.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(a.getCategoryCertificate());
}

@GetMapping("/admission/document/marksheet/{id}")
public ResponseEntity<byte[]> marksheet(@PathVariable Long id){
    AdmissionEntity a = admissionRepo.findById(id).orElse(null);

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=Marksheet.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(a.getMarksheetUpload());
}




@GetMapping("/student/admission/download/{id}")
public void downloadAdmissionForm(@PathVariable Long id,
                                  HttpServletResponse response) throws Exception {

    AdmissionEntity admission =
            admissionRepo.findById(id)
                         .orElseThrow(() -> new RuntimeException("Admission not found"));

    Context context = new Context();
    context.setVariable("admission", admission);

    context.setVariable("photoUrl",
            "http://localhost:8080/admission/photo/" + admission.getId());

    context.setVariable("signatureUrl",
            "http://localhost:8080/admission/signature/" + admission.getId());

    // downloadAdmissionForm.html को process करेगा
    String html = templateEngine.process(
            "Student/downloadAdmissionForm", context);

    response.setContentType("text/html");
    response.setHeader(
            "Content-Disposition",
            "attachment; filename=AdmissionForm.html");

    OutputStream out = response.getOutputStream();
    out.write(html.getBytes());
    out.flush();
    out.close();
}

    @Autowired
    private PaymentRepo paymentRepo;

    @GetMapping("/payment-dashboard")
    public String paymentDashboard(Model model){

        model.addAttribute(
                "totalCollection",
                paymentRepo.getTotalCollectedFee());

        model.addAttribute(
                "totalDue",
                paymentRepo.getTotalDueFee());

        model.addAttribute(
                "paidStudents",
                paymentRepo.countByPaymentStatus("Paid"));

        model.addAttribute(
                "pendingStudents",
                paymentRepo.countByPaymentStatus("Pending"));

        return "Admin/paymentDashboard";
    }
}


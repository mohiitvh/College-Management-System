package com.example.demo.controller;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.AdmissionEntity;
import com.example.demo.entity.CertificateEntity;
import com.example.demo.entity.Student;
import com.example.demo.repo.AdmissionRepo;
import com.example.demo.repo.CertificateRepo;
import com.example.demo.service.CertificateService;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpSession;

@Controller
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private AdmissionRepo admissionRepo;

    @Autowired
    private CertificateRepo certificateRepo;

    // ====================================
    // Student Apply Certificate Page
    // ====================================

    @GetMapping("/certificate/apply")
    public String applyCertificatePage(
            HttpSession session,
            Model model) {

        Student student =
                (Student) session.getAttribute("loggedStudent");

        if (student == null) {
            return "redirect:/loginStudent";
        }

        AdmissionEntity admission =
                admissionRepo.findByStudentId(student.getId());

        if (admission == null ||
                !"APPROVED".equalsIgnoreCase(admission.getStatus())) {

            model.addAttribute(
                    "error",
                    "Your admission is not approved yet.");

            return "Student/admissionNotApproved";
        }

        CertificateEntity certificate =
                new CertificateEntity();

        certificate.setStudentName(student.getName());
        certificate.setEmail(student.getEmail());

        if (admission.getCourse() != null) {
            certificate.setCourseName(
                    admission.getCourse().getCourseName());
        }

        model.addAttribute(
                "certificate",
                certificate);

        return "Student/applyCertificate";
    }

    // ====================================
    // Save Certificate Request
    // ====================================

    @PostMapping("/certificate/apply")
    public String saveCertificate(
            @ModelAttribute CertificateEntity certificate,
            HttpSession session) {

        Student student =
                (Student) session.getAttribute("loggedStudent");

        if (student == null) {
            return "redirect:/loginStudent";
        }

        AdmissionEntity admission =
                admissionRepo.findByStudentId(student.getId());

        if (admission == null) {
            return "redirect:/certificate/apply";
        }

        boolean exists =
                certificateRepo
                        .existsByStudentIdAndCertificateTypeAndStatus(
                                student.getId(),
                                certificate.getCertificateType(),
                                "PENDING");

        if (exists) {
            return "redirect:/student/certificates";
        }

        certificate.setStudentId(student.getId());
        certificate.setStudentName(student.getName());
        certificate.setEmail(student.getEmail());

        if (admission.getCourse() != null) {
            certificate.setCourseName(
                    admission.getCourse().getCourseName());
        }

        certificate.setStatus("PENDING");
        certificate.setApplyDate(LocalDate.now());

        certificateService.save(certificate);

        return "redirect:/student/certificates";
    }

    // ====================================
    // Student Certificate List
    // ====================================

    @GetMapping("/student/certificates")
    public String myCertificates(
            HttpSession session,
            Model model) {

        Student student =
                (Student) session.getAttribute("loggedStudent");

        if (student == null) {
            return "redirect:/loginStudent";
        }

        model.addAttribute(
                "certificates",
                certificateService.findByStudent(student.getId()));

        return "Student/myCertificates";
    }

    // ====================================
    // Admin Certificate Management
    // ====================================

    @GetMapping("/certificate-management")
    public String certificateManagement(
            Model model) {

        model.addAttribute(
                "certificates",
                certificateService.findAll());

        return "Admin/certificateManagement";
    }

    // ====================================
    // Approve Certificate
    // ====================================

    @GetMapping("/certificate/approve/{id}")
    public String approveCertificate(
            @PathVariable Long id) {

        CertificateEntity c =
                certificateService.findById(id);

        if (c == null) {
            return "redirect:/certificate-management";
        }

        c.setStatus("APPROVED");

        c.setApprovedDate(LocalDate.now());

        c.setCertificateNumber(
                "AC-" +
                        LocalDate.now().getYear()
                        + "-"
                        + String.format("%05d", c.getId()));

        certificateRepo.save(c);

        return "redirect:/certificate-management";
    }

    // ====================================
    // Reject Certificate
    // ====================================

    @GetMapping("/certificate/reject/{id}")
    public String rejectCertificate(
            @PathVariable Long id) {

        CertificateEntity c =
                certificateService.findById(id);

        if (c == null) {
            return "redirect:/certificate-management";
        }

        c.setStatus("REJECTED");

        certificateRepo.save(c);

        return "redirect:/certificate-management";
    }

    // ====================================
    // View Certificate (Admin)
    // ====================================

    @GetMapping("/admin/certificate/view/{id}")
    public String viewCertificate(
            @PathVariable Long id,
            Model model) {

        CertificateEntity certificate =
                certificateService.findById(id);

        if (certificate == null) {
            return "redirect:/certificate-management";
        }

        model.addAttribute(
                "certificate",
                certificate);

        return "Admin/viewCertificate";
    }

    // ====================================
    // Download Certificate PDF
    // ====================================

   @GetMapping("/student/certificate/download/{id}")
public ResponseEntity<byte[]> downloadCertificate(
        @PathVariable Long id,
        HttpSession session) {

    Student student =
            (Student) session.getAttribute("loggedStudent");

    if (student == null) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .build();
    }

    CertificateEntity certificate =
            certificateService.findById(id);

    if (certificate == null) {

        return ResponseEntity
                .notFound()
                .build();
    }

    // Security Check

    if (!certificate.getStudentId()
            .equals(student.getId())) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .build();
    }

    // Approved Check

    if (!"APPROVED".equalsIgnoreCase(
            certificate.getStatus())) {

        return ResponseEntity
                .badRequest()
                .build();
    }

    try {

        ByteArrayOutputStream out =
                new ByteArrayOutputStream();

        Document document =
                new Document(
                        PageSize.A4,
                        40,
                        40,
                        40,
                        40);

        PdfWriter writer =
                PdfWriter.getInstance(
                        document,
                        out);

        document.open();

        // ==========================
        // BORDER
        // ==========================

        PdfContentByte canvas =
                writer.getDirectContent();

        Rectangle rect =
                new Rectangle(
                        30,
                        30,
                        565,
                        812);

        rect.setBorder(Rectangle.BOX);

        rect.setBorderWidth(4);

        rect.setBorderColor(
                new java.awt.Color(
                        13,
                        110,
                        253));

        canvas.rectangle(rect);


        // ==========================
        // FONTS
        // ==========================

        Font titleFont =
                new Font(
                        Font.HELVETICA,
                        28,
                        Font.BOLD,
                        new java.awt.Color(
                                13,
                                110,
                                253));

        Font subTitleFont =
                new Font(
                        Font.HELVETICA,
                        20,
                        Font.BOLD);

        Font normalFont =
                new Font(
                        Font.HELVETICA,
                        14);

        Font nameFont =
                new Font(
                        Font.HELVETICA,
                        26,
                        Font.BOLD,
                        new java.awt.Color(
                                220,
                                53,
                                69));


        // ==========================
        // COLLEGE NAME
        // ==========================

        Paragraph college =
                new Paragraph(
                        "APNA COLLEGE",
                        titleFont);

        college.setAlignment(
                Element.ALIGN_CENTER);

        document.add(college);


        // ==========================
        // CERTIFICATE TITLE
        // ==========================

        Paragraph title =
                new Paragraph(

                        certificate
                                .getCertificateType()
                                .toUpperCase(),

                        subTitleFont);

        title.setAlignment(
                Element.ALIGN_CENTER);

        title.setSpacingBefore(15);

        document.add(title);


        // ==========================
        // CERTIFICATE TEXT
        // ==========================

        Paragraph msg1 =
                new Paragraph(

                        "This is to certify that",

                        normalFont);

        msg1.setAlignment(
                Element.ALIGN_CENTER);

        msg1.setSpacingBefore(40);

        document.add(msg1);


        // ==========================
        // STUDENT NAME
        // ==========================

        Paragraph studentName =
                new Paragraph(

                        certificate
                                .getStudentName(),

                        nameFont);

        studentName.setAlignment(
                Element.ALIGN_CENTER);

        studentName.setSpacingBefore(20);

        document.add(studentName);


        // ==========================
        // COURSE
        // ==========================

        Paragraph course =
                new Paragraph(

                        "Course : "

                                + certificate
                                .getCourseName(),

                        normalFont);

        course.setAlignment(
                Element.ALIGN_CENTER);

        course.setSpacingBefore(20);

        document.add(course);


        // ==========================
        // EMAIL
        // ==========================

        Paragraph email =
                new Paragraph(

                        "Email : "

                                + certificate
                                .getEmail(),

                        normalFont);

        email.setAlignment(
                Element.ALIGN_CENTER);

        email.setSpacingBefore(10);

        document.add(email);


        // ==========================
        // STUDENT ID
        // ==========================

        Paragraph sid =
                new Paragraph(

                        "Student ID : "

                                + certificate
                                .getStudentId(),

                        normalFont);

        sid.setAlignment(
                Element.ALIGN_CENTER);

        sid.setSpacingBefore(10);

        document.add(sid);


        // ==========================
        // DESCRIPTION
        // ==========================

        Paragraph msg2 =
                new Paragraph(

                        "has successfully fulfilled all requirements "
                                + "of the institution and is hereby "
                                + "awarded this certificate.",

                        normalFont);

        msg2.setAlignment(
                Element.ALIGN_CENTER);

        msg2.setSpacingBefore(40);

        document.add(msg2);


        // ==========================
        // CERTIFICATE NUMBER
        // ==========================

        Paragraph certNo =
                new Paragraph(

                        "Certificate No : "

                                + certificate
                                .getCertificateNumber(),

                        normalFont);

        certNo.setAlignment(
                Element.ALIGN_CENTER);

        certNo.setSpacingBefore(40);

        document.add(certNo);


        // ==========================
        // ISSUE DATE
        // ==========================

        Paragraph issueDate =
                new Paragraph(

                        "Issue Date : "

                                + certificate
                                .getApprovedDate()
                                .format(
                                        DateTimeFormatter
                                                .ofPattern(
                                                        "dd-MM-yyyy")),

                        normalFont);

        issueDate.setAlignment(
                Element.ALIGN_CENTER);

        issueDate.setSpacingBefore(10);

        document.add(issueDate);


        // ==========================
        // SIGNATURE
        // ==========================

        Paragraph sign =
                new Paragraph(

                        "Authorized Signature",

                        subTitleFont);

        sign.setAlignment(
                Element.ALIGN_RIGHT);

        sign.setSpacingBefore(70);

        document.add(sign);


        document.close();


        HttpHeaders headers =
                new HttpHeaders();

        headers.add(
                HttpHeaders.CONTENT_TYPE,
                "application/pdf");

        headers.add(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=certificate.pdf");


        return new ResponseEntity<>(

                out.toByteArray(),

                headers,

                HttpStatus.OK);

    }

    catch (Exception e) {

        e.printStackTrace();

        return ResponseEntity
                .internalServerError()
                .build();
    }

    
} 
}
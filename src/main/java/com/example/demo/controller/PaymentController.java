package com.example.demo.controller;

import java.time.LocalDate;
import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.AdmissionEntity;
import com.example.demo.entity.PaymentEntity;
import java.util.List;
import com.example.demo.entity.Student;
import com.example.demo.repo.AdmissionRepo;
import com.example.demo.service.PaymentService;


@Controller
@RequestMapping("/student/payment")
public class PaymentController {

	@Autowired
	private AdmissionRepo admissionRepo;
	
    @Autowired
    private PaymentService paymentService;

    // Fee Payment Page

    @GetMapping("/feePayment")
public String feePayment(
        HttpSession session,
        Model model) {

    Student student =
            (Student) session.getAttribute("loggedStudent");

    if(student == null){
        return "redirect:/loginStudent";
    }

    AdmissionEntity admission =
            admissionRepo.findByStudentId(
                    student.getId());

    // Admission not filled

    if(admission == null){

        return "Student/admissionRequired";
    }

    PaymentEntity payment =
            paymentService.getPaymentByStudentId(
                    student.getId());

    // Payment completed

    if(payment != null
            && "PAID".equals(
            payment.getPaymentStatus())){

        model.addAttribute(
                "payment",
                payment);

        return "Student/alreadyPayment";
    }

    model.addAttribute(
            "payment",
            payment);

    return "Student/feePayment";
}

    // Pay Fee

    @PostMapping("/pay")
public String payFee(
        @RequestParam Double paidAmount,
        @RequestParam String paymentMode,
        HttpSession session) {

    Student student =
            (Student) session.getAttribute(
                    "loggedStudent");

    if(student == null){

        return "redirect:/loginStudent";
    }

    PaymentEntity payment =
            paymentService.getPaymentByStudentId(
                    student.getId());

    if(payment == null){

        return "redirect:/student/payment/feePayment";
    }

    double oldPaid =
            payment.getPaidAmount() == null
            ? 0
            : payment.getPaidAmount();

    payment.setPaidAmount(
            oldPaid + paidAmount);

    payment.setPaymentMode(
            paymentMode);

    payment.setTransactionId(
            "TXN"
            + System.currentTimeMillis());

    payment.setPaymentDate(
            LocalDate.now());

    paymentService.savePayment(payment);

    return "redirect:/student/payment/feeHistory";
}

    // History

//   @GetMapping("/feeHistory")
//public String feeHistory(
//        HttpSession session,
//        Model model){
//
//    Student student =
//            (Student) session.getAttribute(
//                    "loggedStudent");
//
//    if(student == null){
//
//        return "redirect:/loginStudent";
//    }
//
//    PaymentEntity payment =
//            paymentService.getPaymentByStudentId(
//                    student.getId());
//
//    model.addAttribute(
//            "payment",
//            payment);
//
//    return "Student/feeHistory";
//}
    
    @GetMapping("/feeHistory")
    public String feeHistory(
            HttpSession session,
            Model model){

        Student student =
                (Student) session.getAttribute(
                        "loggedStudent");

        if(student == null){

            return "redirect:/loginStudent";
        }

        // Admission Check
        AdmissionEntity admission =
                admissionRepo.findByStudentId(
                        student.getId());

        if(admission == null){

            return "Student/admissionRequired";
        }

        PaymentEntity payment =
                paymentService.getPaymentByStudentId(
                        student.getId());

        model.addAttribute(
                "payment",
                payment);

        return "Student/feeHistory";
    }
    
    
    
    // Details

    @GetMapping("/feeDetails")
public String feeDetails(
        HttpSession session,
        Model model) {

    Student student =
            (Student) session.getAttribute("loggedStudent");

    if(student == null){

        return "redirect:/loginStudent";
    }

    AdmissionEntity admission =
            admissionRepo.findByStudentId(
                    student.getId());

    if(admission == null){

        return "Student/admissionRequired";
    }

    PaymentEntity payment =
            paymentService
            .getPaymentByStudentId(
                    student.getId());

    if(payment == null){

        return "redirect:/student/payment/feePayment";
    }

    model.addAttribute(
            "payment",
            payment);

    return "Student/feeDetails";
}
    
    @GetMapping("/receipt")
    public String studentReceipt(
            HttpSession session,
            Model model) {

        Student student =
                (Student) session.getAttribute("loggedStudent");

        if(student == null){

            return "redirect:/loginStudent";
        }


        AdmissionEntity admission =
                admissionRepo.findByStudentId(
                        student.getId());

        if(admission == null){

            return "Student/admissionRequired";
        }


        PaymentEntity payment =
                paymentService
                .getPaymentByStudentId(
                        student.getId());

        if(payment == null){

            return "redirect:/student/payment/feePayment";
        }

        model.addAttribute(
                "payment",
                payment);

        return "Receipt/receipt";
    }
}
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.PaymentEntity;
import com.example.demo.service.PaymentService;

@Controller
@RequestMapping("/admin/payment")
public class AdminPaymentController {

    @Autowired
    private PaymentService paymentService;

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalPayments",
                paymentService.getAllPayments().size());

        model.addAttribute("paidCount",
                paymentService.getPaidStudents().size());

        model.addAttribute("pendingCount",
                paymentService.getPendingStudents().size());

        model.addAttribute("dueCount",
                paymentService.getDueStudents().size());

        return "Admin/paymentDashboard";
    }

    // All Payments
    @GetMapping("/list")
    public String paymentList(Model model) {

        List<PaymentEntity> payments =
                paymentService.getAllPayments();

        model.addAttribute("payments", payments);

        return "Admin/paymentList";
    }

    // Payment Details
    @GetMapping("/details/{id}")
    public String paymentDetails(
            @PathVariable Long id,
            Model model) {

        PaymentEntity payment =
                paymentService.getPaymentById(id)
                        .orElse(null);

        model.addAttribute("payment", payment);

        return "Admin/paymentDetails";
    }

    // Edit Payment
    @GetMapping("/edit/{id}")
    public String editPayment(
            @PathVariable Long id,
            Model model) {

        PaymentEntity payment =
                paymentService.getPaymentById(id)
                        .orElse(null);

        model.addAttribute("payment", payment);

        return "Admin/editPayment";
    }

    // Update Payment
    @PostMapping("/update")
    public String updatePayment(
            @ModelAttribute PaymentEntity payment) {

        paymentService.savePayment(payment);

        return "redirect:/admin/payment/list";
    }

    // Delete Payment
    @GetMapping("/delete/{id}")
    public String deletePayment(
            @PathVariable Long id) {

        paymentService.deletePayment(id);

        return "redirect:/admin/payment/list";
    }

    // Paid Students
    @GetMapping("/paid")
    public String paidStudents(Model model) {

        model.addAttribute(
                "payments",
                paymentService.getPaidStudents());

        return "Admin/paidStudents";
    }

    // Pending Students
    @GetMapping("/pending")
    public String pendingStudents(Model model) {

        model.addAttribute(
                "payments",
                paymentService.getPendingStudents());

        return "Admin/pendingStudents";
    }

    // Due Students
    @GetMapping("/due")
    public String dueStudents(Model model) {

        model.addAttribute(
                "payments",
                paymentService.getDueStudents());

        return "Admin/dueStudents";
    }
    
    
}
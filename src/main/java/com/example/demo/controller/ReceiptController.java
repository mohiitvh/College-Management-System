package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.PaymentEntity;
import com.example.demo.service.PaymentService;

@Controller
@RequestMapping("/receipt")
public class ReceiptController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{id}")
    public String receipt(
            @PathVariable Long id,
            Model model) {

        PaymentEntity payment =
                paymentService
                .getPaymentById(id)
                .orElse(null);

        if(payment == null){
            return "redirect:/admin/payment/list";
        }

        model.addAttribute("payment", payment);

        return "Receipt/receipt";
    }
}
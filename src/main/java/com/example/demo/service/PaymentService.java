package com.example.demo.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AdmissionEntity;
import com.example.demo.entity.CourseEntity;
import com.example.demo.entity.PaymentEntity;
import com.example.demo.repo.AdmissionRepo;
import com.example.demo.repo.PaymentRepo;

@Service
public class PaymentService {

	@Autowired
	private AdmissionRepo admissionRepo;
    @Autowired
    private PaymentRepo paymentRepo;

    // =========================
    // Get All Payments
    // =========================

    public List<PaymentEntity> getAllPayments() {
        return paymentRepo.findAll();
    }

    // =========================
    // Get Payment By ID
    // =========================

    public Optional<PaymentEntity> getPaymentById(Long id) {
        return paymentRepo.findById(id);
    }

    // =========================
    // Get Payment By Student ID
    // =========================

    public PaymentEntity getPaymentByStudentId(Long studentId) {

        List<PaymentEntity> payments =
                paymentRepo.findAllByStudentId(studentId);

        if(payments.isEmpty()) {
            return null;
        }

        PaymentEntity payment =
                payments.get(payments.size() - 1);

        AdmissionEntity admission =
                admissionRepo.findByStudentId(studentId);

        if(admission != null){

            payment.setStudentName(
                    admission.getFullName());

            if(admission.getCourse() != null){
                payment.setCourseName(
                        admission.getCourse()
                                 .getCourseName());
            }
        }

        return payment;
    }
    // =========================
    // Paid Students
    // =========================

    public List<PaymentEntity> getPaidStudents() {
        return paymentRepo.findByPaymentStatus("PAID");
    }

    // =========================
    // Pending Students
    // =========================

    public List<PaymentEntity> getPendingStudents() {
        return paymentRepo.findByPaymentStatus("PENDING");
    }

    // =========================
    // Due Students
    // =========================

    public List<PaymentEntity> getDueStudents() {
        return paymentRepo.findByDueAmountGreaterThan(0.0);
    }

    // =========================
    // Save / Update Payment
    // =========================

    public void savePayment(PaymentEntity payment) {

        if (payment.getPaidAmount() == null) {
            payment.setPaidAmount(0.0);
        }

        if (payment.getTotalFee() == null) {
            payment.setTotalFee(0.0);
        }

        double totalFee =
                payment.getTotalFee() == null
                        ? 0
                        : payment.getTotalFee();

        double paidAmount =
                payment.getPaidAmount() == null
                        ? 0
                        : payment.getPaidAmount();

        double due = totalFee - paidAmount;

        if(due < 0){
            due = 0;
        }

        payment.setDueAmount(due);

        // Payment Status

        if (due <= 0) {

            payment.setPaymentStatus("PAID");

        } else if (payment.getPaidAmount() > 0) {

            payment.setPaymentStatus("PARTIAL");

        } else {

            payment.setPaymentStatus("PENDING");
        }

        // Transaction ID

        if (payment.getTransactionId() == null
                || payment.getTransactionId().isEmpty()) {

            payment.setTransactionId(
                    "TXN" + System.currentTimeMillis());
        }

        // Payment Date

        if (payment.getPaymentDate() == null) {

            payment.setPaymentDate(
                    LocalDate.now());
        }

        paymentRepo.save(payment);
    }

    // =========================
    // Delete Payment
    // =========================

    public void deletePayment(Long id) {
        paymentRepo.deleteById(id);
    }

    // =========================
    // Auto Create Payment
    // After Admission Save
    // =========================

    public void createPaymentFromAdmission(
            AdmissionEntity admission) {

        if (admission == null
                || admission.getCourse() == null) {
            return;
        }

        // Already Exists

        if (!paymentRepo.findAllByStudentId(
                admission.getStudentId()).isEmpty()) {
            return;
        }
        CourseEntity course =
                admission.getCourse();

        PaymentEntity payment =
                new PaymentEntity();

        payment.setStudentId(
                admission.getStudentId());

        payment.setStudentName(
                admission.getFullName());

        payment.setCourseName(
                course.getCourseName());

        payment.setAdmissionFee(
                course.getAdmissionFee());

        payment.setSemesterFee(
                course.getSemesterFee());

        payment.setTotalFee(
                course.getTotalFee());

        payment.setPaidAmount(0.0);

        payment.setDueAmount(
                course.getTotalFee());

        payment.setPaymentStatus(
                "PENDING");

        payment.setPaymentMode(
                "NA");

        payment.setTransactionId(
                "ADM" + System.currentTimeMillis());

        payment.setPaymentDate(
                LocalDate.now());

        paymentRepo.save(payment);
    }

    // =========================
    // Total Collection
    // =========================

    public Double getTotalCollection() {

        Double amount =
                paymentRepo.getTotalCollectedFee();

        return amount == null ? 0.0 : amount;
    }

 // =========================
 // Pending Payments Count
 // =========================

 public Long getPendingPaymentsCount() {

     Long count = paymentRepo.countByPaymentStatus("PENDING");

     return count == null ? 0L : count;
 }
    // =========================
    // Total Due
    // =========================

    public Double getTotalDueAmount() {

        Double amount =
                paymentRepo.getTotalDueFee();

        return amount == null ? 0.0 : amount;
    }
}
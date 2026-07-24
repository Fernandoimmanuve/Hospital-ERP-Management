package com.hospital.erp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // =====================================
    // SAVE PAYMENT
    // =====================================

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // =====================================
    // UPDATE PAYMENT
    // =====================================

    @Override
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // =====================================
    // DELETE PAYMENT
    // =====================================

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    // =====================================
    // GET PAYMENT BY ID
    // =====================================

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    // =====================================
    // GET PAYMENT BY BILL ID
    // =====================================

    @Override
    public Optional<Payment> getPaymentByBillId(Long billId) {
        return paymentRepository.findByBillId(billId);
    }

    // =====================================
    // GET ALL PAYMENTS
    // =====================================

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // =====================================
    // GET PAYMENTS BY STATUS
    // =====================================

    @Override
    public List<Payment> getPaymentsByStatus(String status) {
        return paymentRepository.findByPaymentStatus(status);
    }

    // =====================================
    // GET PAYMENTS BY METHOD
    // =====================================

    @Override
    public List<Payment> getPaymentsByMethod(String paymentMethod) {
        return paymentRepository.findByPaymentMethod(paymentMethod);
    }

    // =====================================
    // CHECK TRANSACTION ID
    // =====================================

    @Override
    public boolean existsByTransactionId(String transactionId) {
        return paymentRepository.existsByTransactionId(transactionId);
    }

    // =====================================
    // DASHBOARD COUNTS
    // =====================================

    @Override
    public long getTotalPayments() {
        return paymentRepository.count();
    }

    @Override
    public long getSuccessfulPayments() {
        return paymentRepository.countByPaymentStatus("SUCCESS");
    }

    @Override
    public long getFailedPayments() {
        return paymentRepository.countByPaymentStatus("FAILED");
    }
}
package com.hospital.erp;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Payment savePayment(Payment payment);

    Payment updatePayment(Payment payment);

    void deletePayment(Long id);

    Optional<Payment> getPaymentById(Long id);

    Optional<Payment> getPaymentByBillId(Long billId);

    List<Payment> getAllPayments();

    List<Payment> getPaymentsByStatus(String status);

    List<Payment> getPaymentsByMethod(String paymentMethod);

    boolean existsByTransactionId(String transactionId);

    long getTotalPayments();

    long getSuccessfulPayments();

    long getFailedPayments();
}
package com.hospital.erp;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Find payment by Bill ID
    Optional<Payment> findByBillId(Long billId);

    // Find all payments by Status
    List<Payment> findByPaymentStatus(String paymentStatus);

    // Find all payments by Payment Method
    List<Payment> findByPaymentMethod(String paymentMethod);

    // Check Transaction ID
    boolean existsByTransactionId(String transactionId);

    // Dashboard Count
    long countByPaymentStatus(String paymentStatus);
}
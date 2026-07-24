package com.hospital.erp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Bill ID
    @Column(nullable = false)
    private Long billId;

    // Amount Paid
    @Column(nullable = false)
    private Double amount;

    // Payment Method
    @Column(length = 50)
    private String paymentMethod;

    // Transaction ID
    @Column(unique = true, nullable = false)
    private String transactionId;

    // Payment Status
    @Column(length = 30)
    private String paymentStatus;

    // Payment Date
    private LocalDateTime paymentDate;

    // Constructors

    public Payment() {
    }

    public Payment(Long billId,
                   Double amount,
                   String paymentMethod,
                   String transactionId,
                   String paymentStatus,
                   LocalDateTime paymentDate) {

        this.billId = billId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }

    // ==========================
    // GETTERS AND SETTERS
    // ==========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
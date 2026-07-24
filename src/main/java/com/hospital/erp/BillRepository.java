package com.hospital.erp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {


    // Find bills by patient id
    List<Bill> findByPatientId(Long patientId);


    // Find bills by payment status
    List<Bill> findByPaymentStatus(String paymentStatus);


    // Find bills by bill type
    List<Bill> findByBillType(String billType);


    // Count payment status
    long countByPaymentStatus(String paymentStatus);


}
package com.hospital.erp;

import java.util.List;
import java.util.Optional;

public interface BillService {

    // =====================================
    // SAVE BILL
    // =====================================

    Bill saveBill(Bill bill);

    // =====================================
    // UPDATE BILL
    // =====================================

    Bill updateBill(Bill bill);

    // =====================================
    // DELETE BILL
    // =====================================

    void deleteBill(Long id);

    // =====================================
    // GET BILL BY ID
    // =====================================

    Optional<Bill> getBillById(Long id);

    // =====================================
    // GET ALL BILLS
    // =====================================

    List<Bill> getAllBills();

    // =====================================
    // GET PATIENT BILLS
    // =====================================

    List<Bill> getBillsByPatientId(Long patientId);

    // =====================================
    // GET BILLS BY STATUS
    // =====================================

    List<Bill> getBillsByPaymentStatus(String paymentStatus);

    // =====================================
    // GET BILLS BY TYPE
    // =====================================

    List<Bill> getBillsByType(String billType);

    // =====================================
    // DASHBOARD
    // =====================================

    long getTotalBills();

    long getPaidBills();

    long getPendingBills();
}
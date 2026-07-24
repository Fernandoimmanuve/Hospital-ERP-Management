package com.hospital.erp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    // =====================================
    // SAVE BILL
    // =====================================

    @Override
    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }

    // =====================================
    // UPDATE BILL
    // =====================================

    @Override
    public Bill updateBill(Bill bill) {
        return billRepository.save(bill);
    }

    // =====================================
    // DELETE BILL
    // =====================================

    @Override
    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

    // =====================================
    // GET BILL BY ID
    // =====================================

    @Override
    public Optional<Bill> getBillById(Long id) {
        return billRepository.findById(id);
    }

    // =====================================
    // GET ALL BILLS
    // =====================================

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    // =====================================
    // GET BILLS BY PATIENT
    // =====================================

    @Override
    public List<Bill> getBillsByPatientId(Long patientId) {
        return billRepository.findByPatientId(patientId);
    }

    // =====================================
    // GET BILLS BY STATUS
    // =====================================

    @Override
    public List<Bill> getBillsByPaymentStatus(String paymentStatus) {
        return billRepository.findByPaymentStatus(paymentStatus);
    }

    // =====================================
    // GET BILLS BY TYPE
    // =====================================

    @Override
    public List<Bill> getBillsByType(String billType) {
        return billRepository.findByBillType(billType);
    }

    // =====================================
    // DASHBOARD COUNTS
    // =====================================

    @Override
    public long getTotalBills() {
        return billRepository.count();
    }

    @Override
    public long getPaidBills() {
        return billRepository.countByPaymentStatus("PAID");
    }

    @Override
    public long getPendingBills() {
        return billRepository.countByPaymentStatus("PENDING");
    }
}
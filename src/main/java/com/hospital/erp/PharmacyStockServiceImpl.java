package com.hospital.erp;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PharmacyStockServiceImpl implements PharmacyStockService {

    private final MedicineService medicineService;
    private final MedicineStockTransactionService transactionService;

    public PharmacyStockServiceImpl(
            MedicineService medicineService,
            MedicineStockTransactionService transactionService) {

        this.medicineService = medicineService;
        this.transactionService = transactionService;
    }

    // =====================================
    // ISSUE MEDICINE
    // =====================================

    @Override
    public boolean issueMedicine(
            String medicineName,
            int quantity,
            Long prescriptionId,
            String pharmacistName) {

        Medicine medicine = medicineService
                .findByMedicineName(medicineName)
                .orElse(null);

        if (medicine == null) {
            return false;
        }

        Integer currentStock = medicine.getQuantity();

        if (currentStock == null) {
            currentStock = 0;
        }

        // Insufficient stock
        if (currentStock < quantity) {
            return false;
        }

        int remainingStock = currentStock - quantity;

        // Update medicine stock
        medicine.setQuantity(remainingStock);
        medicineService.updateMedicine(medicine);

        // Create transaction
        MedicineStockTransaction transaction = new MedicineStockTransaction();

        transaction.setMedicineId(medicine.getId());
        transaction.setMedicineName(medicine.getMedicineName());
        transaction.setTransactionType("ISSUE");
        transaction.setQuantity(quantity);
        transaction.setPreviousStock(currentStock);
        transaction.setRemainingStock(remainingStock);
        transaction.setPrescriptionId(prescriptionId);
        transaction.setPharmacistName(pharmacistName);
        transaction.setTransactionDate(LocalDate.now());
        transaction.setRemarks("Medicine issued against prescription");

        transactionService.saveTransaction(transaction);

        return true;
    }
}
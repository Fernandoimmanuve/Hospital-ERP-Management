package com.hospital.erp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicineStockTransactionService {

    // =====================================
    // SAVE
    // =====================================

    MedicineStockTransaction saveTransaction(
            MedicineStockTransaction transaction
    );

    // =====================================
    // UPDATE
    // =====================================

    MedicineStockTransaction updateTransaction(
            MedicineStockTransaction transaction
    );

    // =====================================
    // DELETE
    // =====================================

    void deleteTransaction(Long id);

    // =====================================
    // GET BY ID
    // =====================================

    Optional<MedicineStockTransaction> getTransactionById(Long id);

    // =====================================
    // GET ALL
    // =====================================

    List<MedicineStockTransaction> getAllTransactions();

    // =====================================
    // MEDICINE HISTORY
    // =====================================

    List<MedicineStockTransaction> getTransactionsByMedicine(Long medicineId);

    // =====================================
    // SEARCH MEDICINE
    // =====================================

    List<MedicineStockTransaction> searchMedicine(String medicineName);

    // =====================================
    // ISSUE HISTORY
    // =====================================

    List<MedicineStockTransaction> getIssueTransactions();

    // =====================================
    // PURCHASE HISTORY
    // =====================================

    List<MedicineStockTransaction> getPurchaseTransactions();

    // =====================================
    // PRESCRIPTION HISTORY
    // =====================================

    List<MedicineStockTransaction> getTransactionsByPrescription(Long prescriptionId);

    // =====================================
    // PHARMACIST HISTORY
    // =====================================

    List<MedicineStockTransaction> getTransactionsByPharmacist(String pharmacistName);

    // =====================================
    // DATE FILTER
    // =====================================

    List<MedicineStockTransaction> getTransactionsByDate(LocalDate date);

    // =====================================
    // DASHBOARD
    // =====================================

    long getTotalTransactions();

    long getIssueCount();

    long getPurchaseCount();
}
package com.hospital.erp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MedicineStockTransactionRepository
        extends JpaRepository<MedicineStockTransaction, Long> {

    // Find transactions by medicine
    List<MedicineStockTransaction> findByMedicineId(Long medicineId);

    // Find transactions by medicine name
    List<MedicineStockTransaction> findByMedicineNameContainingIgnoreCase(String medicineName);

    // Find by transaction type (ISSUE, PURCHASE, RETURN)
    List<MedicineStockTransaction> findByTransactionType(String transactionType);

    // Find by prescription
    List<MedicineStockTransaction> findByPrescriptionId(Long prescriptionId);

    // Find by pharmacist
    List<MedicineStockTransaction> findByPharmacistNameContainingIgnoreCase(String pharmacistName);

    // Find by transaction date
    List<MedicineStockTransaction> findByTransactionDate(LocalDate transactionDate);

    // Dashboard counts
    long countByTransactionType(String transactionType);
}
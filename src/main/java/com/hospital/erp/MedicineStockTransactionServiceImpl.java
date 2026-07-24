package com.hospital.erp;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineStockTransactionServiceImpl implements MedicineStockTransactionService {

    private final MedicineStockTransactionRepository repository;

    public MedicineStockTransactionServiceImpl(
            MedicineStockTransactionRepository repository) {

        this.repository = repository;
    }

    // =====================================
    // SAVE
    // =====================================

    @Override
    public MedicineStockTransaction saveTransaction(
            MedicineStockTransaction transaction) {

        return repository.save(transaction);
    }

    // =====================================
    // UPDATE
    // =====================================

    @Override
    public MedicineStockTransaction updateTransaction(
            MedicineStockTransaction transaction) {

        return repository.save(transaction);
    }

    // =====================================
    // DELETE
    // =====================================

    @Override
    public void deleteTransaction(Long id) {

        repository.deleteById(id);
    }

    // =====================================
    // GET BY ID
    // =====================================

    @Override
    public Optional<MedicineStockTransaction> getTransactionById(Long id) {

        return repository.findById(id);
    }

    // =====================================
    // GET ALL
    // =====================================

    @Override
    public List<MedicineStockTransaction> getAllTransactions() {

        return repository.findAll();
    }

    // =====================================
    // GET BY MEDICINE
    // =====================================

    @Override
    public List<MedicineStockTransaction> getTransactionsByMedicine(Long medicineId) {

        return repository.findByMedicineId(medicineId);
    }

    // =====================================
    // SEARCH MEDICINE
    // =====================================

    @Override
    public List<MedicineStockTransaction> searchMedicine(String medicineName) {

        return repository.findByMedicineNameContainingIgnoreCase(medicineName);
    }

    // =====================================
    // ISSUE TRANSACTIONS
    // =====================================

    @Override
    public List<MedicineStockTransaction> getIssueTransactions() {

        return repository.findByTransactionType("ISSUE");
    }

    // =====================================
    // PURCHASE TRANSACTIONS
    // =====================================

    @Override
    public List<MedicineStockTransaction> getPurchaseTransactions() {

        return repository.findByTransactionType("PURCHASE");
    }

    // =====================================
    // PRESCRIPTION HISTORY
    // =====================================

    @Override
    public List<MedicineStockTransaction> getTransactionsByPrescription(Long prescriptionId) {

        return repository.findByPrescriptionId(prescriptionId);
    }

    // =====================================
    // PHARMACIST HISTORY
    // =====================================

    @Override
    public List<MedicineStockTransaction> getTransactionsByPharmacist(String pharmacistName) {

        return repository.findByPharmacistNameContainingIgnoreCase(pharmacistName);
    }

    // =====================================
    // DATE FILTER
    // =====================================

    @Override
    public List<MedicineStockTransaction> getTransactionsByDate(LocalDate date) {

        return repository.findByTransactionDate(date);
    }

    // =====================================
    // DASHBOARD
    // =====================================

    @Override
    public long getTotalTransactions() {

        return repository.count();
    }

    @Override
    public long getIssueCount() {

        return repository.countByTransactionType("ISSUE");
    }

    @Override
    public long getPurchaseCount() {

        return repository.countByTransactionType("PURCHASE");
    }
}
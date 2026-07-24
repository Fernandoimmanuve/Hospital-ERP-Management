package com.hospital.erp;

import java.util.List;
import java.util.Optional;

public interface MedicineService {

    // =====================================
    // SAVE
    // =====================================

    Medicine saveMedicine(Medicine medicine);

    // =====================================
    // UPDATE
    // =====================================

    Medicine updateMedicine(Medicine medicine);

    // =====================================
    // DELETE
    // =====================================

    void deleteMedicine(Long id);

    // =====================================
    // GET BY ID
    // =====================================

    Optional<Medicine> getMedicineById(Long id);

    // =====================================
    // GET ALL
    // =====================================

    List<Medicine> getAllMedicines();

    // =====================================
    // FIND BY NAME
    // =====================================

    Optional<Medicine> findByMedicineName(String medicineName);

    // =====================================
    // SEARCH
    // =====================================

    List<Medicine> searchByMedicineName(String keyword);

    // =====================================
    // SEARCH CATEGORY
    // =====================================

    List<Medicine> searchByCategory(String category);

    // =====================================
    // STATUS
    // =====================================

    List<Medicine> getMedicinesByStatus(String status);

    // =====================================
    // EXPIRED MEDICINES
    // =====================================

    List<Medicine> getExpiredMedicines();

    // =====================================
    // LOW STOCK
    // =====================================

    List<Medicine> getLowStockMedicines(Integer quantity);

    // =====================================
    // BATCH NUMBER
    // =====================================

    boolean existsByBatchNumber(String batchNumber);

    // =====================================
    // DASHBOARD COUNTS
    // =====================================

    long getTotalMedicineCount();

    long getAvailableMedicineCount();

    long getLowStockCount();

    long getExpiredMedicineCount();
}
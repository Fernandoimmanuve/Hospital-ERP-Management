package com.hospital.erp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    // =====================================
    // FIND BY MEDICINE NAME
    // =====================================

    Optional<Medicine> findByMedicineName(String medicineName);

    // =====================================
    // SEARCH MEDICINE NAME
    // =====================================

    List<Medicine> findByMedicineNameContainingIgnoreCase(String medicineName);

    // =====================================
    // CATEGORY
    // =====================================

    List<Medicine> findByCategory(String category);

    // =====================================
    // STATUS
    // =====================================

    List<Medicine> findByStatus(String status);

    long countByStatus(String status);

    // =====================================
    // BATCH NUMBER
    // =====================================

    Optional<Medicine> findByBatchNumber(String batchNumber);

    boolean existsByBatchNumber(String batchNumber);

    // =====================================
    // EXPIRY DATE
    // =====================================

    List<Medicine> findByExpiryDateBefore(LocalDate date);

    // =====================================
    // LOW STOCK
    // =====================================

    List<Medicine> findByQuantityLessThanEqual(Integer quantity);
}
package com.hospital.erp;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    // =====================================
    // SAVE
    // =====================================

    @Override
    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    // =====================================
    // UPDATE
    // =====================================

    @Override
    public Medicine updateMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    // =====================================
    // DELETE
    // =====================================

    @Override
    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }

    // =====================================
    // GET BY ID
    // =====================================

    @Override
    public Optional<Medicine> getMedicineById(Long id) {
        return medicineRepository.findById(id);
    }

    // =====================================
    // GET ALL
    // =====================================

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    // =====================================
    // FIND BY NAME
    // =====================================

    @Override
    public Optional<Medicine> findByMedicineName(String medicineName) {
        return medicineRepository.findByMedicineName(medicineName);
    }

    // =====================================
    // SEARCH BY NAME
    // =====================================

    @Override
    public List<Medicine> searchByMedicineName(String keyword) {
        return medicineRepository.findByMedicineNameContainingIgnoreCase(keyword);
    }

    // =====================================
    // SEARCH BY CATEGORY
    // =====================================

    @Override
    public List<Medicine> searchByCategory(String category) {
        return medicineRepository.findByCategory(category);
    }

    // =====================================
    // STATUS
    // =====================================

    @Override
    public List<Medicine> getMedicinesByStatus(String status) {
        return medicineRepository.findByStatus(status);
    }

    // =====================================
    // EXPIRED MEDICINES
    // =====================================

    @Override
    public List<Medicine> getExpiredMedicines() {
        return medicineRepository.findByExpiryDateBefore(LocalDate.now());
    }

    // =====================================
    // LOW STOCK
    // =====================================

    @Override
    public List<Medicine> getLowStockMedicines(Integer quantity) {
        return medicineRepository.findByQuantityLessThanEqual(quantity);
    }

    // =====================================
    // EXISTS BY BATCH NUMBER
    // =====================================

    @Override
    public boolean existsByBatchNumber(String batchNumber) {
        return medicineRepository.existsByBatchNumber(batchNumber);
    }

    // =====================================
    // DASHBOARD COUNTS
    // =====================================

    @Override
    public long getTotalMedicineCount() {
        return medicineRepository.count();
    }

    @Override
    public long getAvailableMedicineCount() {
        return medicineRepository.countByStatus("Available");
    }

    @Override
    public long getLowStockCount() {
        return medicineRepository.findByQuantityLessThanEqual(20).size();
    }

    @Override
    public long getExpiredMedicineCount() {
        return medicineRepository.findByExpiryDateBefore(LocalDate.now()).size();
    }
}
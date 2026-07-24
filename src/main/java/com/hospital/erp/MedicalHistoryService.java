package com.hospital.erp;

import java.util.List;
import java.util.Optional;

public interface MedicalHistoryService {

    // Save Medical History
    MedicalHistory saveHistory(MedicalHistory medicalHistory);

    // Get History by ID
    Optional<MedicalHistory> getHistoryById(Long id);

    // Get All History
    List<MedicalHistory> getAllHistory();

    // Get History by Patient ID
    List<MedicalHistory> getHistoryByPatientId(Long patientId);

    // Update History
    MedicalHistory updateHistory(MedicalHistory medicalHistory);

    // Delete History
    void deleteHistory(Long id);

}
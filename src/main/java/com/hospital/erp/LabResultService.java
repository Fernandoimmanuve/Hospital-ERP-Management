package com.hospital.erp;

import java.util.List;

public interface LabResultService {

    // Get all lab reports
    List<LabResult> getAllResults();

    // Get report by ID
    LabResult getResultById(Long id);

    // Get patient reports
    List<LabResult> getResultsByPatientId(Long patientId);

    // Save report
    LabResult saveResult(LabResult labResult);

    // Update report
    LabResult updateResult(LabResult labResult);

    // Delete report
    void deleteResult(Long id);
}
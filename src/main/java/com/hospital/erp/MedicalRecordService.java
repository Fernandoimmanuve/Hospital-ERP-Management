package com.hospital.erp;


import java.util.List;
import java.util.Optional;



public interface MedicalRecordService {




    // Save medical record

    PatientMedicalRecord saveRecord(
            PatientMedicalRecord record
    );







    // Get record by id

    Optional<PatientMedicalRecord> getRecordById(
            Long id
    );







    // Patient medical history

    List<PatientMedicalRecord> getPatientHistory(
            Long patientId
    );







    // Doctor consultation history

    List<PatientMedicalRecord> getDoctorHistory(
            Long doctorId
    );







    // Search diagnosis

    List<PatientMedicalRecord> searchDiagnosis(
            String diagnosis
    );







    // Get completed records

    List<PatientMedicalRecord> getCompletedRecords();







    // Update record

    PatientMedicalRecord updateRecord(
            PatientMedicalRecord record
    );







    // Delete record

    void deleteRecord(
            Long id
    );



}
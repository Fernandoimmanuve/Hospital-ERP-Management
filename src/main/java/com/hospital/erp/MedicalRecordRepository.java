package com.hospital.erp;


import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface MedicalRecordRepository 
        extends JpaRepository<PatientMedicalRecord, Long> {




    // Patient medical history

    List<PatientMedicalRecord> 
    findByPatientIdOrderByVisitDateDesc(
            Long patientId
    );







    // Doctor consultation records

    List<PatientMedicalRecord> 
    findByDoctorIdOrderByVisitDateDesc(
            Long doctorId
    );







    // Search diagnosis history

    List<PatientMedicalRecord>
    findByDiagnosisContainingIgnoreCase(
            String diagnosis
    );







    // Completed medical records

    List<PatientMedicalRecord>
    findByStatus(
            String status
    );



}
package com.hospital.erp;

import java.util.List;
import java.util.Optional;


public interface PrescriptionVerificationService {


    List<PrescriptionVerification> getAllPrescriptionVerifications();


    Optional<PrescriptionVerification> getPrescriptionVerificationById(Long id);


    PrescriptionVerification savePrescriptionVerification(
            PrescriptionVerification prescriptionVerification
    );


    PrescriptionVerification updatePrescriptionVerification(
            PrescriptionVerification prescriptionVerification
    );


    void deletePrescriptionVerification(Long id);



    List<PrescriptionVerification> getByPatientId(Long patientId);



    List<PrescriptionVerification> getByDoctorId(Long doctorId);



    List<PrescriptionVerification> getByStatus(String status);



    // Missing controller methods

    List<PrescriptionVerification> getPendingPrescriptions();


    List<PrescriptionVerification> searchByPatientName(String name);


    long getTotalPrescriptionCount();


    long getPendingCount();


    long getApprovedCount();


    long getRejectedCount();

}
package com.hospital.erp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PrescriptionVerificationRepository 
        extends JpaRepository<PrescriptionVerification, Long> {


    List<PrescriptionVerification> findByPrescriptionId(Long prescriptionId);


    List<PrescriptionVerification> findByDoctorNameContainingIgnoreCase(String doctorName);


    List<PrescriptionVerification> findByVerificationStatus(String status);



    List<PrescriptionVerification> findByVerificationStatusIgnoreCase(
            String status
    );



    List<PrescriptionVerification> 
    findByPatientNameContainingIgnoreCase(String name);



    long countByVerificationStatus(String status);

}
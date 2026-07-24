package com.hospital.erp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabSampleRepository 
        extends JpaRepository<LabSample, Long> {


    List<LabSample> findByStatus(String status);


    List<LabSample> findByPatientId(Long patientId);


    List<LabSample> findByTestNameContainingIgnoreCase(String testName);

}
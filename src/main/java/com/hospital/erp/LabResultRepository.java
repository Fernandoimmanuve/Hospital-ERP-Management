package com.hospital.erp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LabResultRepository extends JpaRepository<LabResult, Long> {


    List<LabResult> findByPatientId(Long patientId);


}
package com.hospital.erp;

import java.util.List;
import java.util.Optional;

public interface LabSampleService {


    List<LabSample> getAllSamples();


    Optional<LabSample> getSampleById(Long id);


    LabSample saveSample(LabSample labSample);


    LabSample updateSample(LabSample labSample);


    void deleteSample(Long id);


    List<LabSample> getSamplesByPatient(Long patientId);


    List<LabSample> getSamplesByStatus(String status);

}
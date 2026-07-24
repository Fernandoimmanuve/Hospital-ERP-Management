package com.hospital.erp;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LabSampleServiceImpl implements LabSampleService {


    private final LabSampleRepository labSampleRepository;


    public LabSampleServiceImpl(
            LabSampleRepository labSampleRepository) {

        this.labSampleRepository = labSampleRepository;
    }



    @Override
    public List<LabSample> getAllSamples() {

        return labSampleRepository.findAll();
    }



    @Override
    public Optional<LabSample> getSampleById(Long id) {

        return labSampleRepository.findById(id);
    }



    @Override
    public LabSample saveSample(LabSample labSample) {

        return labSampleRepository.save(labSample);
    }



    @Override
    public LabSample updateSample(LabSample labSample) {

        return labSampleRepository.save(labSample);
    }



    @Override
    public void deleteSample(Long id) {

        labSampleRepository.deleteById(id);
    }



    @Override
    public List<LabSample> getSamplesByPatient(Long patientId) {

        return labSampleRepository.findByPatientId(patientId);
    }



    @Override
    public List<LabSample> getSamplesByStatus(String status) {

        return labSampleRepository.findByStatus(status);
    }

}
package com.hospital.erp;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class LabResultServiceImpl implements LabResultService {


    private final LabResultRepository labResultRepository;


    public LabResultServiceImpl(LabResultRepository labResultRepository) {

        this.labResultRepository = labResultRepository;
    }



    @Override
    public List<LabResult> getAllResults() {

        return labResultRepository.findAll();
    }



    @Override
    public LabResult getResultById(Long id) {

        return labResultRepository.findById(id)
                .orElse(null);
    }



    @Override
    public List<LabResult> getResultsByPatientId(Long patientId) {

        return labResultRepository.findByPatientId(patientId);
    }



    @Override
    public LabResult saveResult(LabResult labResult) {

        return labResultRepository.save(labResult);
    }



    @Override
    public LabResult updateResult(LabResult labResult) {

        return labResultRepository.save(labResult);
    }



    @Override
    public void deleteResult(Long id) {

        labResultRepository.deleteById(id);
    }

}
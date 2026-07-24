package com.hospital.erp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistoryServiceImpl(MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    @Override
    public MedicalHistory saveHistory(MedicalHistory medicalHistory) {
        return medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public Optional<MedicalHistory> getHistoryById(Long id) {
        return medicalHistoryRepository.findById(id);
    }

    @Override
    public List<MedicalHistory> getAllHistory() {
        return medicalHistoryRepository.findAll();
    }

    @Override
    public List<MedicalHistory> getHistoryByPatientId(Long patientId) {
        return medicalHistoryRepository.findByPatientIdOrderByVisitDateDesc(patientId);
    }

    @Override
    public MedicalHistory updateHistory(MedicalHistory medicalHistory) {
        return medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public void deleteHistory(Long id) {
        medicalHistoryRepository.deleteById(id);
    }
}

package com.hospital.erp;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {

    private final LaboratoryRepository laboratoryRepository;

    public LaboratoryServiceImpl(LaboratoryRepository laboratoryRepository) {
        this.laboratoryRepository = laboratoryRepository;
    }

    @Override
    public Laboratory saveLaboratory(Laboratory laboratory) {
        return laboratoryRepository.save(laboratory);
    }

    @Override
    public Laboratory updateLaboratory(Laboratory laboratory) {
        return laboratoryRepository.save(laboratory);
    }

    @Override
    public void deleteLaboratory(Long id) {
        laboratoryRepository.deleteById(id);
    }

    @Override
    public Optional<Laboratory> getLaboratoryById(Long id) {
        return laboratoryRepository.findById(id);
    }

    @Override
    public List<Laboratory> getAllLaboratories() {
        return laboratoryRepository.findAll();
    }

    @Override
    public List<Laboratory> getLaboratoriesByPatient(Patient patient) {
        return laboratoryRepository.findByPatient(patient);
    }

    @Override
    public List<Laboratory> getLaboratoriesByDoctor(Doctor doctor) {
        return laboratoryRepository.findByDoctor(doctor);
    }

    @Override
    public List<Laboratory> getLaboratoriesByAppointment(Appointment appointment) {
        return laboratoryRepository.findByAppointment(appointment);
    }

    @Override
    public List<Laboratory> getLaboratoriesByTestDate(LocalDate testDate) {
        return laboratoryRepository.findByTestDate(testDate);
    }

    @Override
    public List<Laboratory> searchByPatientName(String patientName) {
        return laboratoryRepository.findByPatientNameContainingIgnoreCase(patientName);
    }

    @Override
    public List<Laboratory> searchByDoctorName(String doctorName) {
        return laboratoryRepository.findByDoctor_FirstNameContainingIgnoreCase(doctorName);
    }

    @Override
    public List<Laboratory> searchByTestName(String testName) {
        return laboratoryRepository.findByTestNameContainingIgnoreCase(testName);
    }

    @Override
    public List<Laboratory> searchByStatus(String status) {
        return laboratoryRepository.findByStatus(status);
    }

    @Override
    public List<Laboratory> getTodayLaboratoryTests() {
        return laboratoryRepository.findByTestDateOrderByIdDesc(LocalDate.now());
    }

    @Override
    public long getTodayLaboratoryTestCount() {
        return laboratoryRepository.countByTestDate(LocalDate.now());
    }

    @Override
    public long getPendingTestCount() {
        return laboratoryRepository.countByStatus("PENDING");
    }

    @Override
    public long getTotalLaboratoryTestCount() {
        return laboratoryRepository.count();
    }

    @Override
    public boolean existsByAppointment(Appointment appointment) {
        return laboratoryRepository.existsByAppointment(appointment);
    }
}

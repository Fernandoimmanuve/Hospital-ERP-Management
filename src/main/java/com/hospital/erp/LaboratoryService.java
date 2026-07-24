package com.hospital.erp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LaboratoryService {

    // CRUD Operations
    Laboratory saveLaboratory(Laboratory laboratory);

    Laboratory updateLaboratory(Laboratory laboratory);

    void deleteLaboratory(Long id);

    Optional<Laboratory> getLaboratoryById(Long id);

    List<Laboratory> getAllLaboratories();

    // Search & Filter
    List<Laboratory> getLaboratoriesByPatient(Patient patient);

    List<Laboratory> getLaboratoriesByDoctor(Doctor doctor);

    List<Laboratory> getLaboratoriesByAppointment(Appointment appointment);

    List<Laboratory> getLaboratoriesByTestDate(LocalDate testDate);

    List<Laboratory> searchByPatientName(String patientName);

    List<Laboratory> searchByDoctorName(String doctorName);

    List<Laboratory> searchByTestName(String testName);

    List<Laboratory> searchByStatus(String status);

    // Dashboard
    List<Laboratory> getTodayLaboratoryTests();

    long getTodayLaboratoryTestCount();

    long getPendingTestCount();

    long getTotalLaboratoryTestCount();

    // Business Logic
    boolean existsByAppointment(Appointment appointment);

}
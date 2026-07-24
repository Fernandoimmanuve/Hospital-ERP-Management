package com.hospital.erp;

import java.util.List;
import java.util.Optional;

public interface PrescriptionService {

    // =====================================
    // CRUD OPERATIONS
    // =====================================

    Prescription savePrescription(Prescription prescription);

    Prescription updatePrescription(Prescription prescription);

    void deletePrescription(Long id);

    Optional<Prescription> getPrescriptionById(Long id);

    List<Prescription> getAllPrescriptions();

    // =====================================
    // APPOINTMENT
    // =====================================

    boolean existsByAppointment(Appointment appointment);

    Optional<Prescription> getPrescriptionByAppointment(Appointment appointment);

    // =====================================
    // PATIENT
    // =====================================

    List<Prescription> getPrescriptionsByPatientId(Long patientId);

    // =====================================
    // DOCTOR
    // =====================================

    List<Prescription> getDoctorPrescriptions(Long doctorId);

    // =====================================
    // SEARCH
    // =====================================

    List<Prescription> searchByPatientName(String keyword);

    List<Prescription> searchByDoctorName(String keyword);
}
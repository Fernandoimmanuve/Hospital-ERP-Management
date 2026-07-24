package com.hospital.erp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    // =====================================
    // SAVE
    // =====================================

    @Override
    public Prescription savePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    // =====================================
    // UPDATE
    // =====================================

    @Override
    public Prescription updatePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    // =====================================
    // DELETE
    // =====================================

    @Override
    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }

    // =====================================
    // GET BY ID
    // =====================================

    @Override
    public Optional<Prescription> getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id);
    }

    // =====================================
    // GET ALL
    // =====================================

    @Override
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    // =====================================
    // EXISTS BY APPOINTMENT
    // =====================================

    @Override
    public boolean existsByAppointment(Appointment appointment) {
        return prescriptionRepository.existsByAppointment(appointment);
    }

    // =====================================
    // GET BY APPOINTMENT
    // =====================================

    @Override
    public Optional<Prescription> getPrescriptionByAppointment(Appointment appointment) {
        return prescriptionRepository.findByAppointment(appointment);
    }

    // =====================================
    // GET BY PATIENT
    // =====================================

    @Override
    public List<Prescription> getPrescriptionsByPatientId(Long patientId) {
        return prescriptionRepository.findByPatientId(patientId);
    }

    // =====================================
    // GET BY DOCTOR
    // =====================================

    @Override
    public List<Prescription> getDoctorPrescriptions(Long doctorId) {
        return prescriptionRepository.findByDoctorId(doctorId);
    }

    // =====================================
    // SEARCH PATIENT
    // =====================================

    @Override
    public List<Prescription> searchByPatientName(String keyword) {

        return prescriptionRepository.findByPatientNameContainingIgnoreCase(keyword);
    }

    // =====================================
    // SEARCH DOCTOR
    // =====================================

    @Override
    public List<Prescription> searchByDoctorName(String keyword) {

        List<Prescription> prescriptions =
                prescriptionRepository
                        .findByDoctor_FirstNameContainingIgnoreCase(keyword);

        if (prescriptions.isEmpty()) {
            prescriptions =
                    prescriptionRepository
                            .findByDoctor_LastNameContainingIgnoreCase(keyword);
        }

        return prescriptions;
    }
}

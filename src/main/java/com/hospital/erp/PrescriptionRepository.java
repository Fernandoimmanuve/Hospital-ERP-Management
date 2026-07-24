package com.hospital.erp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    // =====================================
    // FIND BY APPOINTMENT
    // =====================================

    Optional<Prescription> findByAppointment(Appointment appointment);

    boolean existsByAppointment(Appointment appointment);

    // =====================================
    // PATIENT
    // =====================================

    List<Prescription> findByPatientId(Long patientId);

    // =====================================
    // DOCTOR
    // =====================================

    List<Prescription> findByDoctorId(Long doctorId);

    // =====================================
    // SEARCH BY PATIENT NAME
    // =====================================

    @Query("SELECT p FROM Prescription p WHERE LOWER(p.patient.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.patient.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Prescription> findByPatientNameContainingIgnoreCase(@Param("keyword") String keyword);

    // =====================================
    // SEARCH BY DOCTOR NAME
    // =====================================

    List<Prescription> findByDoctor_FirstNameContainingIgnoreCase(String firstName);

    List<Prescription> findByDoctor_LastNameContainingIgnoreCase(String lastName);
}

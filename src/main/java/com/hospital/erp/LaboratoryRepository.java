package com.hospital.erp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {

    // Find laboratory tests by patient
    List<Laboratory> findByPatient(Patient patient);

    // Find laboratory tests by doctor
    List<Laboratory> findByDoctor(Doctor doctor);

    // Find laboratory tests by appointment
    List<Laboratory> findByAppointment(Appointment appointment);

    // Find by test name
    List<Laboratory> findByTestNameContainingIgnoreCase(String testName);

    // Find by status
    List<Laboratory> findByStatus(String status);

    // Search by patient name
    @Query("SELECT l FROM Laboratory l WHERE LOWER(l.patient.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(l.patient.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Laboratory> findByPatientNameContainingIgnoreCase(@Param("keyword") String keyword);

    // Search by doctor name
    List<Laboratory> findByDoctor_FirstNameContainingIgnoreCase(String doctorName);

    // Find by test date
    List<Laboratory> findByTestDate(LocalDate testDate);

    // Today's laboratory tests
    List<Laboratory> findByTestDateOrderByIdDesc(LocalDate testDate);

    // Count today's tests
    long countByTestDate(LocalDate testDate);

    // Count by status
    long countByStatus(String status);

    // Check if appointment already has a laboratory request
    boolean existsByAppointment(Appointment appointment);
}

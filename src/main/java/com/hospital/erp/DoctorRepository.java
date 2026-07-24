package com.hospital.erp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // Find doctor by email
    Optional<Doctor> findByEmail(String email);

    // Find doctors by specialization
    List<Doctor> findBySpecialization(String specialization);

    // Find doctors by department
    List<Doctor> findByDepartment(String department);

    // Find doctors by status
    List<Doctor> findByStatus(String status);

    // Find doctor by license number
    Optional<Doctor> findByLicenseNumber(String licenseNumber);

    // Search by first name
    List<Doctor> findByFirstNameContainingIgnoreCase(String firstName);

    // Search by last name
    List<Doctor> findByLastNameContainingIgnoreCase(String lastName);

}
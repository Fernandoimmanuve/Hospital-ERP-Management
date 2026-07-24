package com.hospital.erp;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    // Save Doctor
    Doctor saveDoctor(Doctor doctor);

    // Get Doctor by ID
    Optional<Doctor> getDoctorById(Long id);

    // Get All Doctors
    List<Doctor> getAllDoctors();

    // Get Doctor by Email
    Optional<Doctor> getDoctorByEmail(String email);

    // Search by Specialization
    List<Doctor> getDoctorsBySpecialization(String specialization);

    // Search by Department
    List<Doctor> getDoctorsByDepartment(String department);

    // Search by Status
    List<Doctor> getDoctorsByStatus(String status);

    // Search Doctors
    List<Doctor> searchDoctors(String keyword);

    // Update Doctor
    Doctor updateDoctor(Doctor doctor);

    // Delete Doctor
    void deleteDoctor(Long id);

}
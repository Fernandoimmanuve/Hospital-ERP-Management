package com.hospital.erp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Save Doctor
    @Override
    public Doctor saveDoctor(Doctor doctor) {

        if (doctor.getStatus() == null || doctor.getStatus().isBlank()) {
            doctor.setStatus("ACTIVE");
        }

        return doctorRepository.save(doctor);
    }

    // Get Doctor by ID
    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    // Get All Doctors
    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get Doctor by Email
    @Override
    public Optional<Doctor> getDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

    // Get Doctors by Specialization
    @Override
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }

    // Get Doctors by Department
    @Override
    public List<Doctor> getDoctorsByDepartment(String department) {
        return doctorRepository.findByDepartment(department);
    }

    // Get Doctors by Status
    @Override
    public List<Doctor> getDoctorsByStatus(String status) {
        return doctorRepository.findByStatus(status);
    }

    // Search Doctors
    @Override
    public List<Doctor> searchDoctors(String keyword) {

        List<Doctor> doctors =
                doctorRepository.findByFirstNameContainingIgnoreCase(keyword);

        if (doctors.isEmpty()) {
            doctors =
                    doctorRepository.findByLastNameContainingIgnoreCase(keyword);
        }

        return doctors;
    }

    // Update Doctor
    @Override
    public Doctor updateDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Delete Doctor
    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
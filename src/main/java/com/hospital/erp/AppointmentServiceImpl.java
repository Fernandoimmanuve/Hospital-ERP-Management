package com.hospital.erp;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }


    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }


    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }


    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }


    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }


    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }


    @Override
    public List<Appointment> getAppointmentsByPatient(Patient patient) {
        return appointmentRepository.findByPatient(patient);
    }


    @Override
    public List<Appointment> getAppointmentsByDoctor(Doctor doctor) {
        return appointmentRepository.findByDoctor(doctor);
    }


    @Override
    public List<Appointment> getAppointmentsByDepartment(Department department) {
        return appointmentRepository.findByDepartment(department);
    }


    @Override
    public List<Appointment> getAppointmentsByDate(LocalDate appointmentDate) {
        return appointmentRepository.findByAppointmentDate(appointmentDate);
    }


    @Override
    public List<Appointment> getAppointmentsByStatus(String status) {
        return appointmentRepository.findByStatus(status);
    }


    @Override
    public List<Appointment> searchByPatientName(String patientName) {

        return appointmentRepository
                .findByPatientNameContainingIgnoreCase(patientName);
    }


    // FIXED METHOD
    @Override
    public List<Appointment> searchByDoctorName(String doctorName) {

        return appointmentRepository
                .findByDoctor_FirstNameContainingIgnoreCase(doctorName);
    }


    @Override
    public List<Appointment> searchByDepartmentName(String departmentName) {

        return appointmentRepository
                .findByDepartment_DepartmentNameContainingIgnoreCase(departmentName);
    }


    @Override
    public List<Appointment> getTodayAppointments() {

        return appointmentRepository
                .findByAppointmentDateOrderByAppointmentTimeAsc(
                        LocalDate.now()
                );
    }


    @Override
    public long getTodayAppointmentCount() {

        return appointmentRepository
                .countByAppointmentDate(LocalDate.now());
    }


    @Override
    public long getAppointmentCountByStatus(String status) {

        return appointmentRepository
                .countByStatus(status);
    }


    @Override
    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {

        return appointmentRepository
                .findByDoctorId(doctorId);
    }


    @Override
    public boolean isDoctorAvailable(
            Doctor doctor,
            LocalDate appointmentDate,
            LocalTime appointmentTime
    ) {

        return !appointmentRepository
                .existsByDoctorAndAppointmentDateAndAppointmentTime(
                        doctor,
                        appointmentDate,
                        appointmentTime
                );
    }
}

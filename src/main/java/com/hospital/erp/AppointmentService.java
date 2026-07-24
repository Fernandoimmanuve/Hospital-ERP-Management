package com.hospital.erp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {


    Appointment saveAppointment(Appointment appointment);


    Appointment updateAppointment(Appointment appointment);


    void deleteAppointment(Long id);


    Optional<Appointment> getAppointmentById(Long id);


    List<Appointment> getAllAppointments();


    List<Appointment> getAppointmentsByPatient(Patient patient);


    List<Appointment> getAppointmentsByDoctor(Doctor doctor);


    List<Appointment> getAppointmentsByDepartment(Department department);


    List<Appointment> getAppointmentsByDate(LocalDate appointmentDate);


    List<Appointment> getAppointmentsByStatus(String status);


    List<Appointment> searchByPatientName(String patientName);


    List<Appointment> searchByDoctorName(String doctorName);


    List<Appointment> searchByDepartmentName(String departmentName);


    List<Appointment> getTodayAppointments();


    long getTodayAppointmentCount();


    long getAppointmentCountByStatus(String status);


    List<Appointment> getAppointmentsByDoctorId(Long doctorId);


    boolean isDoctorAvailable(
            Doctor doctor,
            LocalDate appointmentDate,
            LocalTime appointmentTime
    );

}
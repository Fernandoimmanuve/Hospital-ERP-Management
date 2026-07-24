package com.hospital.erp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    List<Appointment> findByPatient(Patient patient);


    List<Appointment> findByDoctor(Doctor doctor);


    List<Appointment> findByDepartment(Department department);


    List<Appointment> findByAppointmentDate(LocalDate appointmentDate);


    List<Appointment> findByStatus(String status);


    @Query("SELECT a FROM Appointment a WHERE LOWER(a.patient.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(a.patient.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Appointment> findByPatientNameContainingIgnoreCase(@Param("keyword") String keyword);


    List<Appointment> findByDoctor_FirstNameContainingIgnoreCase(String firstName);


    List<Appointment> findByDepartment_DepartmentNameContainingIgnoreCase(String departmentName);


    List<Appointment> findByAppointmentDateOrderByAppointmentTimeAsc(LocalDate date);


    long countByAppointmentDate(LocalDate date);


    long countByStatus(String status);


    boolean existsByDoctorAndAppointmentDateAndAppointmentTime(
            Doctor doctor,
            LocalDate appointmentDate,
            LocalTime appointmentTime
    );


    List<Appointment> findByDoctorId(Long doctorId);

}

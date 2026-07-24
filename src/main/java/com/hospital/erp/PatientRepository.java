package com.hospital.erp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {


    // Search patient by first name or last name
    @Query("SELECT p FROM Patient p WHERE LOWER(p.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Patient> searchByKeyword(@Param("keyword") String keyword);


    // Search by status
    List<Patient> findByStatus(String status);


    // Search by email
    List<Patient> findByEmailContainingIgnoreCase(String email);


    // Search by phone
    List<Patient> findByPhoneNumberContaining(String phoneNumber);

}

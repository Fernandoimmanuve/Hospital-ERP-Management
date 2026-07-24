package com.hospital.erp;


import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface FeedbackRepository 
        extends JpaRepository<Feedback, Long> {




    // Get all feedback submitted by patient

    List<Feedback> findByPatientIdOrderByCreatedAtDesc(
            Long patientId
    );







    // Get doctor reviews

    List<Feedback> findByDoctorIdOrderByCreatedAtDesc(
            Long doctorId
    );







    // Get feedback by service type

    List<Feedback> findByServiceType(
            String serviceType
    );







    // Calculate doctor average rating

    List<Feedback> findByDoctorIdAndStatus(
            Long doctorId,
            String status
    );




}
package com.hospital.erp;


import java.util.List;
import java.util.Optional;



public interface FeedbackService {




    // Save feedback

    Feedback saveFeedback(
            Feedback feedback
    );







    // Get feedback by id

    Optional<Feedback> getFeedbackById(
            Long id
    );







    // Get patient feedback history

    List<Feedback> getPatientFeedback(
            Long patientId
    );







    // Get doctor reviews

    List<Feedback> getDoctorReviews(
            Long doctorId
    );







    // Get service feedback

    List<Feedback> getServiceFeedback(
            String serviceType
    );







    // Update feedback

    Feedback updateFeedback(
            Feedback feedback
    );







    // Delete feedback

    void deleteFeedback(
            Long id
    );





}
package com.hospital.erp;





import java.util.List;
import java.util.Optional;



public interface PatientLabRequestService {



    // Save Lab Request

    PatientLabRequest saveLabRequest(
            PatientLabRequest request
    );





    // Update Request

    PatientLabRequest updateLabRequest(
            PatientLabRequest request
    );





    // Delete Request

    void deleteLabRequest(
            Long id
    );





    // Find By ID

    Optional<PatientLabRequest> getLabRequestById(
            Long id
    );





    // Get All Requests

    List<PatientLabRequest> getAllLabRequests();





    // Patient History

    List<PatientLabRequest> getPatientRequests(
            Long patientId
    );





    // Doctor Requests

    List<PatientLabRequest> getDoctorRequests(
            Long doctorId
    );





    // Test Requests

    List<PatientLabRequest> getTestRequests(
            Long testId
    );





    // Search Patient

    List<PatientLabRequest> searchByPatientName(
            String patientName
    );





    // Search Test

    List<PatientLabRequest> searchByTestName(
            String testName
    );





    // Pending Requests

    List<PatientLabRequest> getPendingRequests();





    // Processing Requests

    List<PatientLabRequest> getProcessingRequests();





    // Completed Requests

    List<PatientLabRequest> getCompletedRequests();





    // Urgent Requests

    List<PatientLabRequest> getUrgentRequests();





    // Dashboard Count

    long getTotalRequests();


    long getPendingCount();


    long getCompletedCount();



}
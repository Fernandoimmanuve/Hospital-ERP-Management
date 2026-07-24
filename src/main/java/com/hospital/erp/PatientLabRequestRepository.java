package com.hospital.erp;




import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;




public interface PatientLabRequestRepository
        extends JpaRepository<PatientLabRequest, Long> {




    // =====================================
    // FIND REQUESTS BY PATIENT
    // =====================================

    List<PatientLabRequest> findByPatientId(
            Long patientId
    );






    // =====================================
    // FIND REQUESTS BY DOCTOR
    // =====================================

    List<PatientLabRequest> findByDoctorId(
            Long doctorId
    );






    // =====================================
    // FIND REQUESTS BY TEST
    // =====================================

    List<PatientLabRequest> findByTestId(
            Long testId
    );






    // =====================================
    // FIND BY PATIENT NAME
    // =====================================

    List<PatientLabRequest>
    findByPatientNameContainingIgnoreCase(
            String patientName
    );






    // =====================================
    // FIND BY TEST NAME
    // =====================================

    List<PatientLabRequest>
    findByTestNameContainingIgnoreCase(
            String testName
    );






    // =====================================
    // FIND REQUEST STATUS
    // =====================================

    List<PatientLabRequest> findByStatus(
            String status
    );






    // =====================================
    // FIND PRIORITY REQUESTS
    // =====================================

    List<PatientLabRequest> findByPriority(
            String priority
    );






    // =====================================
    // COUNT REQUEST STATUS
    // =====================================

    long countByStatus(
            String status
    );



}
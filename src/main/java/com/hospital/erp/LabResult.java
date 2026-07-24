package com.hospital.erp;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "lab_results")
public class LabResult {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Patient who owns this report
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;


    // Doctor who requested the test
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;


    private String testName;


    @Column(length = 2000)
    private String resultValue;


    private String reportFile;


    private String status;


    // VERIFIED / PENDING / REJECTED
    @Column(name = "verification_status")
    private String verificationStatus;


    private LocalDateTime createdAt;



    // Constructor
    public LabResult() {

        this.createdAt = LocalDateTime.now();
        this.verificationStatus = "PENDING";
    }



    // Getters and Setters

    public Long getId() {

        return id;
    }


    public void setId(Long id) {

        this.id = id;
    }



    public Patient getPatient() {

        return patient;
    }


    public void setPatient(Patient patient) {

        this.patient = patient;
    }



    public Doctor getDoctor() {

        return doctor;
    }


    public void setDoctor(Doctor doctor) {

        this.doctor = doctor;
    }



    public String getTestName() {

        return testName;
    }


    public void setTestName(String testName) {

        this.testName = testName;
    }



    public String getResultValue() {

        return resultValue;
    }


    public void setResultValue(String resultValue) {

        this.resultValue = resultValue;
    }



    public String getReportFile() {

        return reportFile;
    }


    public void setReportFile(String reportFile) {

        this.reportFile = reportFile;
    }



    public String getStatus() {

        return status;
    }


    public void setStatus(String status) {

        this.status = status;
    }



    public String getVerificationStatus() {

        return verificationStatus;
    }


    public void setVerificationStatus(String verificationStatus) {

        this.verificationStatus = verificationStatus;
    }



    public LocalDateTime getCreatedAt() {

        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {

        this.createdAt = createdAt;
    }

}
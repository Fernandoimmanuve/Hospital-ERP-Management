package com.hospital.erp;


import jakarta.persistence.*;

import java.time.LocalDate;



@Entity
@Table(name="patient_lab_requests")
public class PatientLabRequest {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    // Patient Details

    private Long patientId;


    private String patientName;





    // Doctor Details

    private Long doctorId;


    private String doctorName;





    // Laboratory Test Details

    private Long testId;


    private String testName;





    // Request Date

    private LocalDate requestDate;





    // Priority

    private String priority;





    // Status

    private String status;





    // Additional Notes

    @Column(length = 1000)
    private String remarks;







    // Default Constructor

    public PatientLabRequest(){

    }







    // Parameterized Constructor


    public PatientLabRequest(
            Long id,
            Long patientId,
            String patientName,
            Long doctorId,
            String doctorName,
            Long testId,
            String testName,
            LocalDate requestDate,
            String priority,
            String status,
            String remarks
    ){

        this.id=id;

        this.patientId=patientId;

        this.patientName=patientName;

        this.doctorId=doctorId;

        this.doctorName=doctorName;

        this.testId=testId;

        this.testName=testName;

        this.requestDate=requestDate;

        this.priority=priority;

        this.status=status;

        this.remarks=remarks;

    }







    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }





    public Long getPatientId() {
        return patientId;
    }


    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }





    public String getPatientName() {
        return patientName;
    }


    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }





    public Long getDoctorId() {
        return doctorId;
    }


    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }





    public String getDoctorName() {
        return doctorName;
    }


    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }





    public Long getTestId() {
        return testId;
    }


    public void setTestId(Long testId) {
        this.testId = testId;
    }





    public String getTestName() {
        return testName;
    }


    public void setTestName(String testName) {
        this.testName = testName;
    }





    public LocalDate getRequestDate() {
        return requestDate;
    }


    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }





    public String getPriority() {
        return priority;
    }


    public void setPriority(String priority) {
        this.priority = priority;
    }





    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }





    public String getRemarks() {
        return remarks;
    }


    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
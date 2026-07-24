package com.hospital.erp;


import jakarta.persistence.*;

import java.time.LocalDate;



@Entity
@Table(name="lab_samples")
public class LabSample {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    // Unique Sample Code

    @Column(unique = true)
    private String sampleCode;



    // Patient Details

    private Long patientId;


    private String patientName;



    // Lab Test Details

    private Long testId;


    private String testName;



    // Sample Information

    private String sampleType;



    private LocalDate collectionDate;



    // Technician

    private String technicianName;



    // Status

    private String status;



    // Notes

    @Column(length = 1000)
    private String remarks;






    // Default Constructor

    public LabSample(){

    }






    // Parameterized Constructor

    public LabSample(
            Long id,
            String sampleCode,
            Long patientId,
            String patientName,
            Long testId,
            String testName,
            String sampleType,
            LocalDate collectionDate,
            String technicianName,
            String status,
            String remarks
    ){

        this.id=id;

        this.sampleCode=sampleCode;

        this.patientId=patientId;

        this.patientName=patientName;

        this.testId=testId;

        this.testName=testName;

        this.sampleType=sampleType;

        this.collectionDate=collectionDate;

        this.technicianName=technicianName;

        this.status=status;

        this.remarks=remarks;

    }






    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }





    public String getSampleCode() {
        return sampleCode;
    }


    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
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





    public String getSampleType() {
        return sampleType;
    }


    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }





    public LocalDate getCollectionDate() {
        return collectionDate;
    }


    public void setCollectionDate(LocalDate collectionDate) {
        this.collectionDate = collectionDate;
    }





    public String getTechnicianName() {
        return technicianName;
    }


    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
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
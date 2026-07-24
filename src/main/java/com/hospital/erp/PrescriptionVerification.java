package com.hospital.erp;


import jakarta.persistence.*;

import java.time.LocalDate;



@Entity
@Table(name = "prescription_verifications")
public class PrescriptionVerification {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    private Long prescriptionId;



    private String patientName;



    private String doctorName;



    private String medicineName;



    private int quantity;



    private String verificationStatus;



    private String pharmacistName;



    private LocalDate verifiedDate;



    @Column(length = 2000)
    private String remarks;




    // Default Constructor

    public PrescriptionVerification() {

    }





    // Parameterized Constructor


    public PrescriptionVerification(
            Long id,
            Long prescriptionId,
            String patientName,
            String doctorName,
            String medicineName,
            int quantity,
            String verificationStatus,
            String pharmacistName,
            LocalDate verifiedDate,
            String remarks
    ) {


        this.id = id;
        this.prescriptionId = prescriptionId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.verificationStatus = verificationStatus;
        this.pharmacistName = pharmacistName;
        this.verifiedDate = verifiedDate;
        this.remarks = remarks;

    }





    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }



    public Long getPrescriptionId() {
        return prescriptionId;
    }


    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }



    public String getPatientName() {
        return patientName;
    }


    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }



    public String getDoctorName() {
        return doctorName;
    }


    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }



    public String getMedicineName() {
        return medicineName;
    }


    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }



    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public String getVerificationStatus() {
        return verificationStatus;
    }


    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }



    public String getPharmacistName() {
        return pharmacistName;
    }


    public void setPharmacistName(String pharmacistName) {
        this.pharmacistName = pharmacistName;
    }



    public LocalDate getVerifiedDate() {
        return verifiedDate;
    }


    public void setVerifiedDate(LocalDate verifiedDate) {
        this.verifiedDate = verifiedDate;
    }



    public String getRemarks() {
        return remarks;
    }


    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
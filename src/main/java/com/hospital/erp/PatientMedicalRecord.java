package com.hospital.erp;


import jakarta.persistence.*;

import java.time.LocalDateTime;



@Entity
@Table(name = "patient_medical_records")
public class PatientMedicalRecord {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;





    private Long patientId;





    private Long doctorId;





    private String doctorName;





    private String patientName;





    @Column(length = 1000)
    private String symptoms;





    @Column(length = 1000)
    private String diagnosis;





    @Column(length = 2000)
    private String treatment;





    @Column(length = 2000)
    private String medicalNotes;





    private String bloodPressure;





    private String bloodSugar;





    private String weight;





    private LocalDateTime visitDate;





    private String status;







    public PatientMedicalRecord(){

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







    public String getPatientName() {

        return patientName;

    }


    public void setPatientName(String patientName) {

        this.patientName = patientName;

    }







    public String getSymptoms() {

        return symptoms;

    }


    public void setSymptoms(String symptoms) {

        this.symptoms = symptoms;

    }







    public String getDiagnosis() {

        return diagnosis;

    }


    public void setDiagnosis(String diagnosis) {

        this.diagnosis = diagnosis;

    }







    public String getTreatment() {

        return treatment;

    }


    public void setTreatment(String treatment) {

        this.treatment = treatment;

    }







    public String getMedicalNotes() {

        return medicalNotes;

    }


    public void setMedicalNotes(String medicalNotes) {

        this.medicalNotes = medicalNotes;

    }







    public String getBloodPressure() {

        return bloodPressure;

    }


    public void setBloodPressure(String bloodPressure) {

        this.bloodPressure = bloodPressure;

    }







    public String getBloodSugar() {

        return bloodSugar;

    }


    public void setBloodSugar(String bloodSugar) {

        this.bloodSugar = bloodSugar;

    }







    public String getWeight() {

        return weight;

    }


    public void setWeight(String weight) {

        this.weight = weight;

    }







    public LocalDateTime getVisitDate() {

        return visitDate;

    }


    public void setVisitDate(LocalDateTime visitDate) {

        this.visitDate = visitDate;

    }







    public String getStatus() {

        return status;

    }


    public void setStatus(String status) {

        this.status = status;

    }



}
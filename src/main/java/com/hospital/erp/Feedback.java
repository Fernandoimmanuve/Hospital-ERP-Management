package com.hospital.erp;


import jakarta.persistence.*;

import java.time.LocalDateTime;



@Entity
@Table(name="feedbacks")
public class Feedback {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private Long patientId;



    private Long doctorId;



    private String doctorName;



    private String serviceType;



    private Integer rating;



    @Column(length = 1000)
    private String comments;



    private String status;



    private LocalDateTime createdAt;






    public Feedback(){

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






    public String getServiceType() {
        return serviceType;
    }


    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }






    public Integer getRating() {
        return rating;
    }


    public void setRating(Integer rating) {
        this.rating = rating;
    }






    public String getComments() {
        return comments;
    }


    public void setComments(String comments) {
        this.comments = comments;
    }






    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }






    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }



}
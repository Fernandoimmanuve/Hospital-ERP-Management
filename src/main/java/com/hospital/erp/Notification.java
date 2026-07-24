package com.hospital.erp;


import jakarta.persistence.*;

import java.time.LocalDateTime;



@Entity
@Table(name="notifications")
public class Notification {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    private Long patientId;




    private String title;



    @Column(length = 500)
    private String message;




    private String type;



    private String status;



    private LocalDateTime createdAt;






    public Notification(){

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






    public String getTitle() {
        return title;
    }




    public void setTitle(String title) {
        this.title = title;
    }







    public String getMessage() {
        return message;
    }




    public void setMessage(String message) {
        this.message = message;
    }






    public String getType() {
        return type;
    }




    public void setType(String type) {
        this.type = type;
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
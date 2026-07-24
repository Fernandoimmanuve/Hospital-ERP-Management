package com.hospital.erp;


import jakarta.persistence.*;

import java.time.LocalDate;



@Entity
@Table(name = "laboratory_tests")
public class LaboratoryTest {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    // Test Name

    @Column(nullable = false)
    private String testName;



    // Test Category

    private String category;



    // Test Description

    @Column(length = 1000)
    private String description;



    // Test Price

    private double price;



    // Normal Result Range

    private String normalRange;



    // Active / Inactive

    private String status;



    // Created Date

    private LocalDate createdDate;





    // ==============================
    // Default Constructor
    // ==============================

    public LaboratoryTest(){

    }






    // ==============================
    // Parameterized Constructor
    // ==============================

    public LaboratoryTest(
            Long id,
            String testName,
            String category,
            String description,
            double price,
            String normalRange,
            String status,
            LocalDate createdDate
    ){

        this.id = id;

        this.testName = testName;

        this.category = category;

        this.description = description;

        this.price = price;

        this.normalRange = normalRange;

        this.status = status;

        this.createdDate = createdDate;

    }






    // ==============================
    // Getters and Setters
    // ==============================


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }






    public String getTestName() {
        return testName;
    }


    public void setTestName(String testName) {
        this.testName = testName;
    }







    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }







    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }







    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }







    public String getNormalRange() {
        return normalRange;
    }


    public void setNormalRange(String normalRange) {
        this.normalRange = normalRange;
    }







    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }







    public LocalDate getCreatedDate() {
        return createdDate;
    }


    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }



}
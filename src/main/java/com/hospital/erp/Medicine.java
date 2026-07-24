package com.hospital.erp;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "medicines")
public class Medicine {

    // =====================================
    // PRIMARY KEY
    // =====================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =====================================
    // BASIC INFORMATION
    // =====================================

    @Column(nullable = false)
    private String medicineName;

    private String category;

    @Column(unique = true)
    private String batchNumber;

    private String manufacturer;

    // =====================================
    // STOCK
    // =====================================

    private Integer quantity;

    private Integer reorderLevel;

    // =====================================
    // PRICE
    // =====================================

    private BigDecimal price;

    // =====================================
    // EXPIRY
    // =====================================

    private LocalDate manufactureDate;

    private LocalDate expiryDate;

    // =====================================
    // STATUS
    // =====================================

    private String status;

    // =====================================
    // DESCRIPTION
    // =====================================

    @Column(length = 1000)
    private String description;

    // =====================================
    // CONSTRUCTORS
    // =====================================

    public Medicine() {
    }

    // =====================================
    // GETTERS & SETTERS
    // =====================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
package com.hospital.erp;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String departmentName;

    @Column(nullable = false, length = 100)
    private String departmentCode;

    @Column(nullable = false, length = 100)
    private String headOfDepartment;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private String status;

    public Department() {
    }

    public Department(Long id, String departmentName,
                      String departmentCode,
                      String headOfDepartment,
                      String description,
                      String location,
                      String contactNumber,
                      String status) {
        this.id = id;
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
        this.headOfDepartment = headOfDepartment;
        this.description = description;
        this.location = location;
        this.contactNumber = contactNumber;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(String headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
package com.hospital.erp;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Find by Department Name
    Optional<Department> findByDepartmentName(String departmentName);

    // Find by Department Code
    Optional<Department> findByDepartmentCode(String departmentCode);

    // Search Department by Name
    List<Department> findByDepartmentNameContainingIgnoreCase(String departmentName);

    // Find Departments by Status
    List<Department> findByStatus(String status);

    // Check Duplicate Department Name
    boolean existsByDepartmentName(String departmentName);

    // Check Duplicate Department Code
    boolean existsByDepartmentCode(String departmentCode);

}
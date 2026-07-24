package com.hospital.erp;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Department saveDepartment(Department department);

    Department updateDepartment(Department department);

    void deleteDepartment(Long id);

    List<Department> getAllDepartments();

    Optional<Department> getDepartmentById(Long id);

    Optional<Department> getDepartmentByName(String departmentName);

    Optional<Department> getDepartmentByCode(String departmentCode);

    List<Department> searchDepartmentsByName(String departmentName);

    List<Department> getDepartmentsByStatus(String status);

    boolean existsByDepartmentName(String departmentName);

    boolean existsByDepartmentCode(String departmentCode);

}
package com.hospital.erp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Optional<Department> getDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName);
    }

    @Override
    public Optional<Department> getDepartmentByCode(String departmentCode) {
        return departmentRepository.findByDepartmentCode(departmentCode);
    }

    @Override
    public List<Department> searchDepartmentsByName(String departmentName) {
        return departmentRepository.findByDepartmentNameContainingIgnoreCase(departmentName);
    }

    @Override
    public List<Department> getDepartmentsByStatus(String status) {
        return departmentRepository.findByStatus(status);
    }

    @Override
    public boolean existsByDepartmentName(String departmentName) {
        return departmentRepository.existsByDepartmentName(departmentName);
    }

    @Override
    public boolean existsByDepartmentCode(String departmentCode) {
        return departmentRepository.existsByDepartmentCode(departmentCode);
    }
}
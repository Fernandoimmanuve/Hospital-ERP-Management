package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // View All Departments
    @GetMapping
    public String viewDepartments(Model model) {

        model.addAttribute("departments", departmentService.getAllDepartments());

        return "view-departments";
    }

    // Show Add Department Page
    @GetMapping("/add")
    public String showAddDepartmentForm(Model model) {

        model.addAttribute("department", new Department());

        return "add-department";
    }

    // Save Department
    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute Department department) {

        if (departmentService.existsByDepartmentName(department.getDepartmentName())) {
            return "redirect:/admin/departments/add?nameExists";
        }

        if (departmentService.existsByDepartmentCode(department.getDepartmentCode())) {
            return "redirect:/admin/departments/add?codeExists";
        }

        departmentService.saveDepartment(department);

        return "redirect:/admin/departments";
    }

    // Show Edit Department Page
    @GetMapping("/edit/{id}")
    public String editDepartment(@PathVariable Long id, Model model) {

        Department department = departmentService.getDepartmentById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        model.addAttribute("department", department);

        return "edit-department";
    }

    // Update Department
    @PostMapping("/update")
    public String updateDepartment(@ModelAttribute Department department) {

        departmentService.updateDepartment(department);

        return "redirect:/admin/departments";
    }

    // Delete Department
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return "redirect:/admin/departments";
    }

    // Search Department
    @GetMapping("/search")
    public String searchDepartment(@RequestParam("keyword") String keyword,
                                   Model model) {

        model.addAttribute("departments",
                departmentService.searchDepartmentsByName(keyword));

        return "view-departments";
    }

}
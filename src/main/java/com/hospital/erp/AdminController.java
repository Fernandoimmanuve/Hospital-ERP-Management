package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class AdminController {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final DepartmentRepository departmentRepository;

    public AdminController(DoctorRepository doctorRepository,
                           PatientRepository patientRepository,
                           AppointmentRepository appointmentRepository,
                           DepartmentRepository departmentRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        long doctorCount = doctorRepository.count();
        long patientCount = patientRepository.count();
        long appointmentCount = appointmentRepository.count();
        long departmentCount = departmentRepository.count();

        model.addAttribute("doctorCount", doctorCount);
        model.addAttribute("patientCount", patientCount);
        model.addAttribute("appointmentCount", appointmentCount);
        model.addAttribute("departmentCount", departmentCount);
        model.addAttribute("revenue", appointmentCount * 250);
        model.addAttribute("pendingBills", Math.max(1, appointmentCount / 5));

        // Format today's date
        LocalDate today = LocalDate.now();
        model.addAttribute("todayDate", today.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")));

        return "admin_dashboard";
    }
}

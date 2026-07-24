package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // ===========================
    // View All Doctors
    // ===========================

    @GetMapping
    public String listDoctors(Model model) {

        model.addAttribute("doctors",
                doctorService.getAllDoctors());

        return "view-doctors";
    }

    // ===========================
    // Add Doctor Page
    // ===========================

    @GetMapping("/add")
    public String addDoctorForm(Model model) {

        model.addAttribute("doctor", new Doctor());

        return "add-doctor";
    }

    // ===========================
    // Save Doctor
    // ===========================

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute Doctor doctor) {

        doctorService.saveDoctor(doctor);

        return "redirect:/doctors";
    }

    // ===========================
    // View Doctor
    // ===========================

    @GetMapping("/view/{id}")
    public String viewDoctor(@PathVariable Long id,
                             Model model) {

        Doctor doctor = doctorService.getDoctorById(id)
                .orElseThrow(() ->
                        new RuntimeException("Doctor Not Found"));

        model.addAttribute("doctor", doctor);

        return "view-doctor";
    }

    // ===========================
    // Edit Doctor
    // ===========================

    @GetMapping("/edit/{id}")
    public String editDoctor(@PathVariable Long id,
                             Model model) {

        Doctor doctor = doctorService.getDoctorById(id)
                .orElseThrow(() ->
                        new RuntimeException("Doctor Not Found"));

        model.addAttribute("doctor", doctor);

        return "edit-doctor";
    }

    // ===========================
    // Update Doctor
    // ===========================

    @PostMapping("/update")
    public String updateDoctor(@ModelAttribute Doctor doctor) {

        doctorService.updateDoctor(doctor);

        return "redirect:/doctors";
    }

    // ===========================
    // Delete Doctor
    // ===========================

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {

        doctorService.deleteDoctor(id);

        return "redirect:/doctors";
    }

    @GetMapping("/search")
        public String searchDoctors(@RequestParam String keyword, Model model) {

    model.addAttribute(
            "doctors",
            doctorService.searchDoctors(keyword)
    );

        return "view-doctors";
}

}

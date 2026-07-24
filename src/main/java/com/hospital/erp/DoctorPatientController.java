package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctor/patients")
public class DoctorPatientController {


    private final PatientService patientService;


    public DoctorPatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    // View All Patients
    @GetMapping
    public String viewPatients(Model model) {

        model.addAttribute(
                "patients",
                patientService.getAllPatients()
        );

        return "view-patients";
    }


    // Search Patients
    @GetMapping("/search")
    public String searchPatients(
            @RequestParam("keyword") String keyword,
            Model model
    ) {

        model.addAttribute(
                "patients",
                patientService.searchPatients(keyword)
        );

        return "view-patients";
    }


    // View Patient Details
    @GetMapping("/view/{id}")
    public String viewPatient(
            @PathVariable Long id,
            Model model
    ) {

        Patient patient = patientService
                .getPatientById(id)
                .orElseThrow(() ->
                        new RuntimeException("Patient Not Found")
                );


        model.addAttribute(
                "patient",
                patient
        );


        // Temporary empty lists
        model.addAttribute(
                "medicalHistory",
                List.of()
        );

        model.addAttribute(
                "prescriptions",
                List.of()
        );

        model.addAttribute(
                "labReports",
                List.of()
        );


        return "patient-details";
    }

}
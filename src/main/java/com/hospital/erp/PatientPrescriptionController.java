package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient/prescriptions")
public class PatientPrescriptionController {

    private final PrescriptionService prescriptionService;

    public PatientPrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    // =====================================
    // VIEW ALL PRESCRIPTIONS
    // =====================================

    @GetMapping
    public String viewPrescriptions(Model model) {

        // Temporary Patient ID
        Long patientId = 1L;

        List<Prescription> prescriptions =
                prescriptionService.getPrescriptionsByPatientId(patientId);

        model.addAttribute("prescriptions", prescriptions);

        return "prescriptions";
    }

    // =====================================
    // VIEW PRESCRIPTION DETAILS
    // =====================================

    @GetMapping("/view/{id}")
    public String viewPrescription(@PathVariable Long id,
                                   Model model) {

        Prescription prescription = prescriptionService
                .getPrescriptionById(id)
                .orElseThrow(() ->
                        new RuntimeException("Prescription Not Found"));

        model.addAttribute("prescription", prescription);

        return "prescription-details";
    }
} 
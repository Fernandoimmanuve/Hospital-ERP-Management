package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctor/prescription")
public class DoctorPrescriptionController {

    private final PrescriptionService prescriptionService;

    public DoctorPrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    // =====================================
    // ADD PRESCRIPTION PAGE
    // =====================================

    @GetMapping("/add/{patientId}")
    public String addPrescription(@PathVariable Long patientId,
                                  Model model) {

        Prescription prescription = new Prescription();

        // Store patient ID in the model.
        // The patient will be selected in the form.
        model.addAttribute("patientId", patientId);
        model.addAttribute("prescription", prescription);

        return "add-prescription";
    }

    // =====================================
    // SAVE PRESCRIPTION
    // =====================================

    @PostMapping("/save")
    public String savePrescription(
            @ModelAttribute Prescription prescription) {

        prescriptionService.savePrescription(prescription);

        return "redirect:/doctor/prescription/history";
    }

    // =====================================
    // PRESCRIPTION HISTORY
    // =====================================

    @GetMapping("/history")
    public String prescriptionHistory(Model model) {

        Long doctorId = 1L;   // Temporary

        List<Prescription> prescriptions =
                prescriptionService.getDoctorPrescriptions(doctorId);

        model.addAttribute("prescriptions", prescriptions);

        return "prescription-history";
    }

    // =====================================
    // EDIT
    // =====================================

    @GetMapping("/edit/{id}")
    public String editPrescription(@PathVariable Long id,
                                   Model model) {

        Prescription prescription = prescriptionService
                .getPrescriptionById(id)
                .orElseThrow(() ->
                        new RuntimeException("Prescription Not Found"));

        model.addAttribute("prescription", prescription);

        return "edit-prescription";
    }

    // =====================================
    // UPDATE
    // =====================================

    @PostMapping("/update")
    public String updatePrescription(
            @ModelAttribute Prescription prescription) {

        prescriptionService.updatePrescription(prescription);

        return "redirect:/doctor/prescription/history";
    }

    // =====================================
    // DELETE
    // =====================================

    @GetMapping("/delete/{id}")
    public String deletePrescription(@PathVariable Long id) {

        prescriptionService.deletePrescription(id);

        return "redirect:/doctor/prescription/history";
    }
}
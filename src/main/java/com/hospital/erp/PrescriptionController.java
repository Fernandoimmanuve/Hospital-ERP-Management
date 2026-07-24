package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    public PrescriptionController(
            PrescriptionService prescriptionService,
            PatientService patientService,
            DoctorService doctorService,
            AppointmentService appointmentService) {

        this.prescriptionService = prescriptionService;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    // =====================================
    // VIEW ALL PRESCRIPTIONS
    // =====================================

    @GetMapping
    public String viewPrescriptions(Model model) {

        model.addAttribute(
                "prescriptions",
                prescriptionService.getAllPrescriptions()
        );

        return "view-prescriptions";
    }

    // =====================================
    // ADD PRESCRIPTION PAGE
    // =====================================

    @GetMapping("/add")
    public String showAddPrescriptionForm(Model model) {

        model.addAttribute("prescription", new Prescription());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("appointments", appointmentService.getAllAppointments());

        return "add-prescription";
    }

    // =====================================
    // SAVE PRESCRIPTION
    // =====================================

    @PostMapping("/save")
    public String savePrescription(
            @ModelAttribute Prescription prescription,
            Model model) {

        if (prescription.getAppointment() != null &&
                prescriptionService.existsByAppointment(
                        prescription.getAppointment())) {

            model.addAttribute(
                    "error",
                    "A prescription already exists for this appointment."
            );

            model.addAttribute("prescription", prescription);
            model.addAttribute("patients", patientService.getAllPatients());
            model.addAttribute("doctors", doctorService.getAllDoctors());
            model.addAttribute("appointments", appointmentService.getAllAppointments());

            return "add-prescription";
        }

        prescriptionService.savePrescription(prescription);

        return "redirect:/admin/prescriptions";
    }

    // =====================================
    // EDIT PRESCRIPTION
    // =====================================

    @GetMapping("/edit/{id}")
    public String editPrescription(
            @PathVariable Long id,
            Model model) {

        Prescription prescription = prescriptionService
                .getPrescriptionById(id)
                .orElseThrow(() ->
                        new RuntimeException("Prescription Not Found"));

        model.addAttribute("prescription", prescription);
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("appointments", appointmentService.getAllAppointments());

        return "edit-prescription";
    }

    // =====================================
    // UPDATE PRESCRIPTION
    // =====================================

    @PostMapping("/update")
    public String updatePrescription(
            @ModelAttribute Prescription prescription) {

        prescriptionService.updatePrescription(prescription);

        return "redirect:/admin/prescriptions";
    }

    // =====================================
    // DELETE PRESCRIPTION
    // =====================================

    @GetMapping("/delete/{id}")
    public String deletePrescription(
            @PathVariable Long id) {

        prescriptionService.deletePrescription(id);

        return "redirect:/admin/prescriptions";
    }

    // =====================================
    // SEARCH PRESCRIPTION
    // =====================================

    @GetMapping("/search")
    public String searchPrescription(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute(
                "prescriptions",
                prescriptionService.searchByPatientName(keyword)
        );

        return "view-prescriptions";
    }
}
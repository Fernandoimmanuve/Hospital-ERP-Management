package com.hospital.erp;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Display all patients
    @GetMapping
    public String viewPatients(Model model) {

        model.addAttribute("patients",
                patientService.getAllPatients());

        return "view-patients";
    }

    // Show Add Patient Form
    @GetMapping("/add")
    public String showAddForm(Model model) {

        model.addAttribute("patient", new Patient());

        return "add-patient";
    }

    // Save Patient
    @PostMapping("/save")
    public String savePatient(
            @ModelAttribute("patient") Patient patient) {

        patientService.savePatient(patient);

        return "redirect:/patients";
    }

    // Show Edit Form
    @GetMapping("/edit/{id}")
    public String editPatient(
            @PathVariable Long id,
            Model model) {

        Patient patient = patientService.getPatientById(id)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));

        model.addAttribute("patient", patient);

        return "edit-patient";
    }

    // Update Patient
    @PostMapping("/update")
    public String updatePatient(
            @ModelAttribute Patient patient) {

        patientService.updatePatient(patient);

        return "redirect:/patients";
    }

    // Delete Patient
    @GetMapping("/delete/{id}")
    public String deletePatient(
            @PathVariable Long id) {

        patientService.deletePatient(id);

        return "redirect:/patients";
    }

    // View Patient Details
    @GetMapping("/view/{id}")
    public String viewPatient(
            @PathVariable Long id,
            Model model) {

        Patient patient = patientService.getPatientById(id)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));

        model.addAttribute("patient", patient);

        return "patient-details";
    }

}
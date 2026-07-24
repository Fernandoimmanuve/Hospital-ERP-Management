package com.hospital.erp;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medical-records")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    // =====================================
    // SHOW ADD MEDICAL RECORD PAGE
    // =====================================

    @GetMapping("/add")
    public String showAddMedicalRecordForm(Model model) {

        model.addAttribute("record", new PatientMedicalRecord());

        return "add-record";
    }

    // =====================================
    // SAVE MEDICAL RECORD
    // =====================================

    @PostMapping("/save")
    public String saveMedicalRecord(
            @ModelAttribute("record") PatientMedicalRecord record) {

        medicalRecordService.saveRecord(record);

        return "redirect:/medical-records/history/" + record.getPatientId();
    }

    // =====================================
    // VIEW PATIENT HISTORY
    // =====================================

    @GetMapping("/history/{patientId}")
    public String patientHistory(
            @PathVariable Long patientId,
            Model model) {

        model.addAttribute(
                "records",
                medicalRecordService.getPatientHistory(patientId));

        model.addAttribute("patientId", patientId);

        return "patient-history";
    }

    // =====================================
    // VIEW DOCTOR CONSULTATION HISTORY
    // =====================================

    @GetMapping("/doctor/{doctorId}")
    public String doctorHistory(
            @PathVariable Long doctorId,
            Model model) {

        model.addAttribute(
                "records",
                medicalRecordService.getDoctorHistory(doctorId));

        return "patient-history";
    }

    // =====================================
    // VIEW SINGLE RECORD
    // =====================================

    @GetMapping("/view/{id}")
    public String viewRecord(
            @PathVariable Long id,
            Model model) {

        PatientMedicalRecord record = medicalRecordService
                .getRecordById(id)
                .orElseThrow(() -> new RuntimeException("Medical Record Not Found"));

        model.addAttribute("record", record);

        return "view-record";
    }

    // =====================================
    // SEARCH DIAGNOSIS
    // =====================================

    @GetMapping("/search")
    public String searchDiagnosis(
            @RequestParam String diagnosis,
            Model model) {

        model.addAttribute(
                "records",
                medicalRecordService.searchDiagnosis(diagnosis));

        model.addAttribute("diagnosis", diagnosis);

        return "view-record";
    }

    // =====================================
    // SHOW EDIT PAGE
    // =====================================

    @GetMapping("/edit/{id}")
    public String editRecord(
            @PathVariable Long id,
            Model model) {

        PatientMedicalRecord record = medicalRecordService
                .getRecordById(id)
                .orElseThrow(() -> new RuntimeException("Medical Record Not Found"));

        model.addAttribute("record", record);

        return "edit-record";
    }

    // =====================================
    // UPDATE RECORD
    // =====================================

    @PostMapping("/update")
    public String updateRecord(
            @ModelAttribute("record") PatientMedicalRecord record) {

        medicalRecordService.updateRecord(record);

        return "redirect:/medical-records/view/" + record.getId();
    }

    // =====================================
    // DELETE RECORD
    // =====================================

    @GetMapping("/delete/{id}")
    public String deleteRecord(
            @PathVariable Long id) {

        PatientMedicalRecord record = medicalRecordService
                .getRecordById(id)
                .orElseThrow(() -> new RuntimeException("Medical Record Not Found"));

        Long patientId = record.getPatientId();

        medicalRecordService.deleteRecord(id);

        return "redirect:/medical-records/history/" + patientId;
    }

}
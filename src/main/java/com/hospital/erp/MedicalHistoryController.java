package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient/history")
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;

    public MedicalHistoryController(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    // =====================================
    // VIEW MEDICAL HISTORY
    // =====================================

    @GetMapping
    public String viewHistory(Model model) {

        Long patientId = 1L;   // Temporary Patient ID

        List<MedicalHistory> historyList =
                medicalHistoryService.getHistoryByPatientId(patientId);

        model.addAttribute("history", historyList);

        return "medical-history";
    }

    // =====================================
    // VIEW HISTORY DETAILS
    // =====================================

    @GetMapping("/view/{id}")
    public String viewHistoryDetails(@PathVariable Long id,
                                     Model model) {

        MedicalHistory history = medicalHistoryService
                .getHistoryById(id)
                .orElseThrow(() ->
                        new RuntimeException("Medical History Not Found"));

        model.addAttribute("history", history);

        return "history-details";
    }

}
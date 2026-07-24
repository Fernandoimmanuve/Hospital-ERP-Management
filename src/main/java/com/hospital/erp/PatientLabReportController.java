package com.hospital.erp;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/patient/lab-reports")
public class PatientLabReportController {


    private final LabResultService labResultService;


    public PatientLabReportController(LabResultService labResultService) {

        this.labResultService = labResultService;
    }



    // View patient lab reports
    @GetMapping("/{patientId}")
    public String viewPatientLabReports(
            @PathVariable Long patientId,
            Model model) {


        List<LabResult> labResults =
                labResultService.getResultsByPatientId(patientId);


        model.addAttribute("labResults", labResults);

        model.addAttribute("patientId", patientId);


        return "lab-reports";
    }





    // View single lab report
    @GetMapping("/view/{id}")
    public String viewLabReport(
            @PathVariable Long id,
            Model model) {


        LabResult labResult =
                labResultService.getResultById(id);



        if(labResult == null){

            throw new RuntimeException("Lab Report Not Found");

        }



        model.addAttribute("labResult", labResult);



        return "lab-report-details";
    }





    // Download report page
    @GetMapping("/download/{id}")
    public String downloadReport(
            @PathVariable Long id,
            Model model) {


        LabResult labResult =
                labResultService.getResultById(id);



        if(labResult == null){

            throw new RuntimeException("Lab Report Not Found");

        }



        model.addAttribute("labResult", labResult);



        return "lab-report-details";
    }





    // Search patient's reports by test name
    @GetMapping("/search/{patientId}")
    public String searchPatientReports(
            @PathVariable Long patientId,
            @RequestParam String keyword,
            Model model) {



        List<LabResult> labResults =
                labResultService.getResultsByPatientId(patientId);



        List<LabResult> filteredResults =
                labResults.stream()
                        .filter(result ->
                                result.getTestName()
                                .toLowerCase()
                                .contains(keyword.toLowerCase()))
                        .toList();



        model.addAttribute("labResults", filteredResults);


        model.addAttribute("patientId", patientId);



        return "lab-reports";
    }

}
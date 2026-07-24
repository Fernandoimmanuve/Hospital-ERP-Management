package com.hospital.erp;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/doctor/lab-reports")
public class DoctorLabReportController {


    private final LabResultService labResultService;


    public DoctorLabReportController(LabResultService labResultService) {

        this.labResultService = labResultService;
    }



    // View all lab reports
    @GetMapping
    public String viewLabReports(Model model) {


        List<LabResult> reports =
                labResultService.getAllResults();


        model.addAttribute("labResults", reports);


        return "lab-reports";
    }




    // View single report
    @GetMapping("/view/{id}")
    public String viewReport(
            @PathVariable Long id,
            Model model) {


        LabResult report =
                labResultService.getResultById(id);


        if(report == null){

            throw new RuntimeException("Lab Report Not Found");

        }


        model.addAttribute("labResult", report);


        return "lab-report-details";
    }





    // Show verification page
    @GetMapping("/verify/{id}")
    public String verifyPage(
            @PathVariable Long id,
            Model model) {


        LabResult report =
                labResultService.getResultById(id);


        if(report == null){

            throw new RuntimeException("Lab Report Not Found");

        }


        model.addAttribute("labResult", report);


        return "lab-result-verification";
    }





    // Verify report
    @PostMapping("/verify/{id}")
    public String verifyReport(
            @PathVariable Long id,
            @RequestParam String verificationStatus) {



        LabResult report =
                labResultService.getResultById(id);



        if(report == null){

            throw new RuntimeException("Lab Report Not Found");

        }



        report.setVerificationStatus(verificationStatus);



        labResultService.updateResult(report);



        return "redirect:/doctor/lab-reports";
    }





    // Approve report
    @GetMapping("/approve/{id}")
    public String approveReport(
            @PathVariable Long id) {



        LabResult report =
                labResultService.getResultById(id);



        if(report == null){

            throw new RuntimeException("Lab Report Not Found");

        }



        report.setVerificationStatus("APPROVED");



        labResultService.updateResult(report);



        return "redirect:/doctor/lab-reports";
    }





    // Reject report
    @GetMapping("/reject/{id}")
    public String rejectReport(
            @PathVariable Long id) {



        LabResult report =
                labResultService.getResultById(id);



        if(report == null){

            throw new RuntimeException("Lab Report Not Found");

        }



        report.setVerificationStatus("REJECTED");



        labResultService.updateResult(report);



        return "redirect:/doctor/lab-reports";
    }

}
package com.hospital.erp;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/lab-results")
public class LabResultController {


    private final LabResultService labResultService;


    public LabResultController(LabResultService labResultService) {

        this.labResultService = labResultService;
    }



    // View all lab results
    @GetMapping
    public String viewAllLabResults(Model model) {


        List<LabResult> labResults =
                labResultService.getAllResults();


        model.addAttribute("labResults", labResults);


        return "view-lab-results";
    }





    // Show add page
    @GetMapping("/add")
    public String addLabResultPage(Model model) {


        model.addAttribute("labResult", new LabResult());


        return "add-lab-result";
    }





    // Save lab result
    @PostMapping("/save")
    public String saveLabResult(
            @ModelAttribute("labResult") LabResult labResult) {


        labResultService.saveResult(labResult);


        return "redirect:/lab-results";
    }





    // View single result
    @GetMapping("/view/{id}")
    public String viewLabResult(
            @PathVariable Long id,
            Model model) {


        LabResult labResult =
                labResultService.getResultById(id);


        if(labResult == null){

            throw new RuntimeException("Lab Result Not Found");

        }


        model.addAttribute("labResult", labResult);


        return "view-lab-results";
    }





    // Delete lab result
    @GetMapping("/delete/{id}")
    public String deleteLabResult(
            @PathVariable Long id) {


        labResultService.deleteResult(id);


        return "redirect:/lab-results";
    }





    // Patient wise reports
    @GetMapping("/patient/{patientId}")
    public String patientLabResults(
            @PathVariable Long patientId,
            Model model) {


        List<LabResult> labResults =
                labResultService.getResultsByPatientId(patientId);


        model.addAttribute("labResults", labResults);


        return "view-lab-results";
    }





    // Update lab result
    @PostMapping("/update")
    public String updateLabResult(
            @ModelAttribute("labResult") LabResult labResult) {


        labResultService.updateResult(labResult);


        return "redirect:/lab-results";
    }



}
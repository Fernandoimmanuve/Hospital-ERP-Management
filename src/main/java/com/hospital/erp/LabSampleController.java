package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/lab-samples")
public class LabSampleController {


    private final LabSampleService labSampleService;


    public LabSampleController(LabSampleService labSampleService) {
        this.labSampleService = labSampleService;
    }



    // Display all samples
    @GetMapping
    public String viewAllSamples(Model model) {

        List<LabSample> samples =
                labSampleService.getAllSamples();

        model.addAttribute("samples", samples);

        return "view-lab-samples";
    }



    // Open add sample page
    @GetMapping("/add")
    public String addSamplePage(Model model) {

        model.addAttribute("labSample", new LabSample());

        return "add-lab-sample";
    }



    // Save sample
    @PostMapping("/save")
    public String saveSample(
            @ModelAttribute("labSample") LabSample labSample) {


        labSampleService.saveSample(labSample);

        return "redirect:/lab-samples";
    }



    // View sample by ID
    @GetMapping("/view/{id}")
    public String viewSample(
            @PathVariable Long id,
            Model model) {


        LabSample sample =
                labSampleService.getSampleById(id)
                .orElseThrow(() ->
                        new RuntimeException("Lab Sample Not Found"));


        model.addAttribute("labSample", sample);


        return "view-lab-samples";
    }



    // Edit page
    @GetMapping("/edit/{id}")
    public String editSample(
            @PathVariable Long id,
            Model model) {


        LabSample sample =
                labSampleService.getSampleById(id)
                .orElseThrow(() ->
                        new RuntimeException("Lab Sample Not Found"));


        model.addAttribute("labSample", sample);


        return "edit-lab-sample";
    }



    // Update sample
    @PostMapping("/update")
    public String updateSample(
            @ModelAttribute("labSample") LabSample labSample) {


        labSampleService.updateSample(labSample);


        return "redirect:/lab-samples";
    }



    // Delete sample
    @GetMapping("/delete/{id}")
    public String deleteSample(
            @PathVariable Long id) {


        labSampleService.deleteSample(id);


        return "redirect:/lab-samples";
    }



    // Patient wise samples
    @GetMapping("/patient/{patientId}")
    public String patientSamples(
            @PathVariable Long patientId,
            Model model) {


        List<LabSample> samples =
                labSampleService.getSamplesByPatient(patientId);


        model.addAttribute("samples", samples);


        return "view-lab-samples";
    }



    // Status filter
    @GetMapping("/status/{status}")
    public String statusSamples(
            @PathVariable String status,
            Model model) {


        List<LabSample> samples =
                labSampleService.getSamplesByStatus(status);


        model.addAttribute("samples", samples);


        return "view-lab-samples";
    }

}
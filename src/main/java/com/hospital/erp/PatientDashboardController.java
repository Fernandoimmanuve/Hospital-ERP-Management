package com.hospital.erp;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/patient")
public class PatientDashboardController {



    @GetMapping("/dashboard")
    public String dashboard(
            Model model
    ){


        // Temporary patient data


        model.addAttribute(
                "patientName",
                "Patient"
        );



        model.addAttribute(
                "appointments",
                0
        );



        model.addAttribute(
                "prescriptions",
                0
        );



        model.addAttribute(
                "labReports",
                0
        );



        model.addAttribute(
                "medicalHistory",
                0
        );



        return "patient-dashboard";

    }



}
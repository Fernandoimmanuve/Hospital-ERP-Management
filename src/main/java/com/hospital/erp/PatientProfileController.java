package com.hospital.erp;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/patient/profile")
public class PatientProfileController {



    private final PatientService patientService;



    public PatientProfileController(
            PatientService patientService
    ){

        this.patientService = patientService;

    }







    // =====================================
    // VIEW PROFILE
    // =====================================


    @GetMapping
    public String viewProfile(
            Model model
    ){


        // Temporary patient id

        Long patientId = 1L;



        Patient patient =
                patientService
                .getPatientById(patientId)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Patient Not Found"
                    )
                );



        model.addAttribute(
                "patient",
                patient
        );



        return "profile";

    }









    // =====================================
    // OPEN EDIT PROFILE
    // =====================================


    @GetMapping("/edit")
    public String editProfile(
            Model model
    ){


        Long patientId = 1L;



        Patient patient =
                patientService
                .getPatientById(patientId)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Patient Not Found"
                    )
                );



        model.addAttribute(
                "patient",
                patient
        );



        return "edit-profile";

    }









    // =====================================
    // UPDATE PROFILE
    // =====================================


    @PostMapping("/update")
    public String updateProfile(
            @ModelAttribute Patient patient
    ){



        patientService
                .updatePatient(
                        patient
                );



        return "redirect:/patient/profile";

    }





}
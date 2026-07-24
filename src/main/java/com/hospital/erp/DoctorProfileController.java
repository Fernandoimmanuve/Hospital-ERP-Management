package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/doctor/profile")
public class DoctorProfileController {



    private final DoctorService doctorService;




    public DoctorProfileController(
            DoctorService doctorService
    ){

        this.doctorService = doctorService;

    }








    // =====================================
    // VIEW PROFILE
    // =====================================

    @GetMapping
    public String viewProfile(
            Model model
    ){


        // Temporary doctor id
        Long doctorId = 1L;



        Doctor doctor =
                doctorService
                .getDoctorById(doctorId)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Doctor Not Found"
                    )
                );



        model.addAttribute(
                "doctor",
                doctor
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



        Long doctorId = 1L;



        Doctor doctor =
                doctorService
                .getDoctorById(doctorId)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Doctor Not Found"
                    )
                );



        model.addAttribute(
                "doctor",
                doctor
        );



        return "edit-profile";

    }








    // =====================================
    // UPDATE PROFILE
    // =====================================

    @PostMapping("/update")
    public String updateProfile(
            @ModelAttribute Doctor doctor
    ){



        doctorService
                .updateDoctor(
                        doctor
                );



        return "redirect:/doctor/profile";

    }







}
package com.hospital.erp;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/doctor")
public class DoctorDashboardController {



    @GetMapping("/dashboard")
    public String dashboard(
            Model model
    ){


        model.addAttribute(
                "doctorName",
                "Doctor"
        );


        model.addAttribute(
                "totalPatients",
                0
        );


        model.addAttribute(
                "todayAppointments",
                0
        );


        model.addAttribute(
                "pendingReports",
                0
        );


        model.addAttribute(
                "pendingPrescriptions",
                0
        );



        return "doctor-dashboard";

    }



}
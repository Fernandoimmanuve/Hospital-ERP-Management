package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.*;


import java.util.List;



@Controller
@RequestMapping("/doctor/appointments")
public class DoctorAppointmentController {




    private final AppointmentService appointmentService;







    public DoctorAppointmentController(
            AppointmentService appointmentService
    ){

        this.appointmentService = appointmentService;

    }









    // =====================================
    // VIEW ALL APPOINTMENTS
    // =====================================


    @GetMapping
    public String viewAppointments(
            Model model
    ){



        // Temporary doctor id

        Long doctorId = 1L;




        List<Appointment> appointments =
                appointmentService
                .getAppointmentsByDoctorId(
                        doctorId
                );





        model.addAttribute(
                "appointments",
                appointments
        );



        return "appointments";

    }









    // =====================================
    // VIEW APPOINTMENT DETAILS
    // =====================================


    @GetMapping("/view/{id}")
    public String viewAppointment(
            @PathVariable Long id,
            Model model
    ){



        Appointment appointment =
                appointmentService
                .getAppointmentById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Appointment Not Found"
                    )
                );





        model.addAttribute(
                "appointment",
                appointment
        );



        return "appointment-details";

    }









    // =====================================
    // ACCEPT APPOINTMENT
    // =====================================


    @GetMapping("/accept/{id}")
    public String acceptAppointment(
            @PathVariable Long id
    ){



        Appointment appointment =
                appointmentService
                .getAppointmentById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Appointment Not Found"
                    )
                );





        appointment.setStatus(
                "ACCEPTED"
        );





        appointmentService
                .updateAppointment(
                        appointment
                );





        return "redirect:/doctor/appointments";

    }









    // =====================================
    // REJECT APPOINTMENT
    // =====================================


    @GetMapping("/reject/{id}")
    public String rejectAppointment(
            @PathVariable Long id
    ){



        Appointment appointment =
                appointmentService
                .getAppointmentById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Appointment Not Found"
                    )
                );





        appointment.setStatus(
                "REJECTED"
        );





        appointmentService
                .updateAppointment(
                        appointment
                );





        return "redirect:/doctor/appointments";

    }









    // =====================================
    // COMPLETE APPOINTMENT
    // =====================================


    @GetMapping("/complete/{id}")
    public String completeAppointment(
            @PathVariable Long id
    ){



        Appointment appointment =
                appointmentService
                .getAppointmentById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Appointment Not Found"
                    )
                );





        appointment.setStatus(
                "COMPLETED"
        );





        appointmentService
                .updateAppointment(
                        appointment
                );





        return "redirect:/doctor/appointments";

    }





}
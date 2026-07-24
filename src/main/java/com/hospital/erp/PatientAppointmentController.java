package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient/appointments")
public class PatientAppointmentController {


    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;


    public PatientAppointmentController(
            AppointmentService appointmentService,
            DoctorService doctorService,
            PatientService patientService) {

        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }



    // =====================================
    // BOOK APPOINTMENT PAGE
    // =====================================

    @GetMapping("/book")
    public String bookAppointmentPage(Model model) {


        model.addAttribute(
                "appointment",
                new Appointment()
        );


        model.addAttribute(
                "doctors",
                doctorService.getAllDoctors()
        );


        return "book-appointment";
    }




    // =====================================
    // SAVE APPOINTMENT
    // =====================================

    @PostMapping("/save")
    public String saveAppointment(
            @ModelAttribute("appointment") Appointment appointment) {


        appointment.setStatus("Pending");


        appointmentService.saveAppointment(appointment);


        return "redirect:/patient/appointments";
    }




    // =====================================
    // VIEW MY APPOINTMENTS
    // =====================================

    @GetMapping
    public String viewAppointments(Model model) {


        /*
          Temporary patient login.
          Later replace with logged-in patient ID
        */

        Long patientId = 1L;


        Patient patient = patientService
                .getPatientById(patientId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Patient not found"
                        ));



        List<Appointment> appointments =
                appointmentService
                .getAppointmentsByPatient(patient);



        model.addAttribute(
                "appointments",
                appointments
        );


        return "appointments";
    }





    // =====================================
    // CANCEL APPOINTMENT
    // =====================================

    @GetMapping("/cancel/{id}")
    public String cancelAppointment(
            @PathVariable Long id) {


        Appointment appointment =
                appointmentService
                .getAppointmentById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Appointment Not Found"
                        ));



        appointment.setStatus("Cancelled");



        appointmentService.updateAppointment(
                appointment
        );



        return "redirect:/patient/appointments";
    }

}
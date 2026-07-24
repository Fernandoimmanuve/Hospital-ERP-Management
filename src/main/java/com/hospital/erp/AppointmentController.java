package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/appointments")
public class AppointmentController {


    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final DepartmentService departmentService;


    public AppointmentController(
            AppointmentService appointmentService,
            DoctorService doctorService,
            PatientService patientService,
            DepartmentService departmentService) {

        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.departmentService = departmentService;
    }


    // View all appointments
    @GetMapping
    public String viewAppointments(Model model) {

        model.addAttribute(
                "appointments",
                appointmentService.getAllAppointments()
        );

        return "view-appointments";
    }



    // Open appointment booking page
    @GetMapping("/add")
    public String showAppointmentForm(Model model) {


        model.addAttribute(
                "appointment",
                new Appointment()
        );


        model.addAttribute(
                "patients",
                patientService.getAllPatients()
        );


        model.addAttribute(
                "doctors",
                doctorService.getAllDoctors()
        );


        model.addAttribute(
                "departments",
                departmentService.getAllDepartments()
        );


        return "book-appointment";
    }




    // Save appointment
    @PostMapping("/save")
    public String saveAppointment(
            @ModelAttribute("appointment") Appointment appointment,
            Model model) {


        Doctor doctor = appointment.getDoctor();


        if (doctor != null &&
                !appointmentService.isDoctorAvailable(
                        doctor,
                        appointment.getAppointmentDate(),
                        appointment.getAppointmentTime()
                )) {


            model.addAttribute(
                    "doctorUnavailable",
                    "Doctor is already booked for this date and time."
            );


            loadAppointmentData(model, appointment);


            return "book-appointment";
        }



        appointmentService.saveAppointment(appointment);


        return "redirect:/admin/appointments";
    }





    // Edit appointment
    @GetMapping("/edit/{id}")
    public String editAppointment(
            @PathVariable Long id,
            Model model) {


        Appointment appointment =
                appointmentService.getAppointmentById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Appointment not found"
                        ));



        model.addAttribute(
                "appointment",
                appointment
        );


        loadAppointmentData(model, appointment);


        return "edit-appointment";
    }






    // Update appointment
    @PostMapping("/update")
    public String updateAppointment(
            @ModelAttribute("appointment") Appointment appointment) {


        appointmentService.updateAppointment(appointment);


        return "redirect:/admin/appointments";
    }






    // Delete appointment
    @GetMapping("/delete/{id}")
    public String deleteAppointment(
            @PathVariable Long id) {


        appointmentService.deleteAppointment(id);


        return "redirect:/admin/appointments";
    }






    // Search appointment
    @GetMapping("/search")
    public String searchAppointment(
            @RequestParam("keyword") String keyword,
            Model model) {


        model.addAttribute(
                "appointments",
                appointmentService.searchByPatientName(keyword)
        );


        return "view-appointments";
    }






    // Common dropdown loading method
    private void loadAppointmentData(
            Model model,
            Appointment appointment) {


        model.addAttribute(
                "patients",
                patientService.getAllPatients()
        );


        model.addAttribute(
                "doctors",
                doctorService.getAllDoctors()
        );


        model.addAttribute(
                "departments",
                departmentService.getAllDepartments()
        );

    }

}
package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/laboratory")
public class LaboratoryController {


    private final LaboratoryService laboratoryService;

    private final PatientService patientService;

    private final DoctorService doctorService;

    private final AppointmentService appointmentService;


    public LaboratoryController(
            LaboratoryService laboratoryService,
            PatientService patientService,
            DoctorService doctorService,
            AppointmentService appointmentService) {

        this.laboratoryService = laboratoryService;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }



    // ==============================
    // VIEW ALL LAB TESTS
    // ==============================

    @GetMapping
    public String viewLaboratoryTests(Model model) {


        model.addAttribute(
                "laboratories",
                laboratoryService.getAllLaboratories()
        );


        return "view-laboratory";

    }



    // ==============================
    // OPEN ADD LAB TEST PAGE
    // ==============================

    @GetMapping("/add")
    public String showAddLaboratoryForm(Model model) {


        model.addAttribute(
                "laboratory",
                new Laboratory()
        );


        loadDropdownData(model);


        return "add-laboratory";

    }



    // ==============================
    // SAVE LAB TEST
    // ==============================

    @PostMapping("/save")
    public String saveLaboratory(
            @ModelAttribute Laboratory laboratory,
            Model model) {


        Appointment appointment =
                laboratory.getAppointment();


        if(laboratoryService.existsByAppointment(appointment)){


            model.addAttribute(
                    "error",
                    "Laboratory request already exists for this appointment."
            );


            model.addAttribute(
                    "laboratory",
                    laboratory
            );


            loadDropdownData(model);


            return "add-laboratory";

        }


        laboratoryService.saveLaboratory(laboratory);


        return "redirect:/admin/laboratory";


    }



    // ==============================
    // EDIT LAB TEST
    // ==============================

    @GetMapping("/edit/{id}")
    public String editLaboratory(
            @PathVariable Long id,
            Model model) {


        Laboratory laboratory =
                laboratoryService
                .getLaboratoryById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Laboratory Test Not Found"
                        )
                );


        model.addAttribute(
                "laboratory",
                laboratory
        );


        loadDropdownData(model);


        return "edit-laboratory";

    }



    // ==============================
    // UPDATE LAB TEST
    // ==============================

    @PostMapping("/update")
    public String updateLaboratory(
            @ModelAttribute Laboratory laboratory) {


        laboratoryService.updateLaboratory(laboratory);


        return "redirect:/admin/laboratory";

    }



    // ==============================
    // DELETE LAB TEST
    // ==============================

    @GetMapping("/delete/{id}")
    public String deleteLaboratory(
            @PathVariable Long id) {


        laboratoryService.deleteLaboratory(id);


        return "redirect:/admin/laboratory";

    }



    // ==============================
    // SEARCH BY PATIENT NAME
    // ==============================

    @GetMapping("/search")
    public String searchLaboratory(
            @RequestParam String keyword,
            Model model) {


        model.addAttribute(
                "laboratories",
                laboratoryService
                .searchByPatientName(keyword)
        );


        return "view-laboratory";

    }



    // ==============================
    // SEARCH BY STATUS
    // ==============================

    @GetMapping("/status/{status}")
    public String searchByStatus(
            @PathVariable String status,
            Model model) {


        model.addAttribute(
                "laboratories",
                laboratoryService
                .searchByStatus(status)
        );


        return "view-laboratory";

    }




    // ==============================
    // LOAD DROPDOWN DATA
    // ==============================

    private void loadDropdownData(Model model){


        model.addAttribute(
                "patients",
                patientService.getAllPatients()
        );


        model.addAttribute(
                "doctors",
                doctorService.getAllDoctors()
        );


        model.addAttribute(
                "appointments",
                appointmentService.getAllAppointments()
        );

    }


}
package com.hospital.erp;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;



@Controller
@RequestMapping("/admin/lab-request")
public class PatientLabRequestController {



    private final PatientLabRequestService labRequestService;





    public PatientLabRequestController(
            PatientLabRequestService labRequestService
    ) {


        this.labRequestService = labRequestService;

    }






    // =====================================
    // VIEW ALL LAB REQUESTS
    // =====================================

    @GetMapping
    public String viewLabRequests(
            Model model
    ) {


        model.addAttribute(
                "requests",
                labRequestService
                .getAllLabRequests()
        );


        return "view-lab-requests";

    }








    // =====================================
    // OPEN ADD REQUEST PAGE
    // =====================================

    @GetMapping("/add")
    public String showAddRequestForm(
            Model model
    ) {


        model.addAttribute(
                "labRequest",
                new PatientLabRequest()
        );


        return "add-lab-request";

    }








    // =====================================
    // SAVE LAB REQUEST
    // =====================================

    @PostMapping("/save")
    public String saveLabRequest(
            @ModelAttribute PatientLabRequest request
    ) {



        if(request.getRequestDate()==null)
        {

            request.setRequestDate(
                    LocalDate.now()
            );

        }





        if(request.getStatus()==null)
        {

            request.setStatus(
                    "Pending"
            );

        }





        if(request.getPriority()==null)
        {

            request.setPriority(
                    "NORMAL"
            );

        }




        labRequestService
                .saveLabRequest(
                        request
                );



        return "redirect:/admin/lab-request";

    }








    // =====================================
    // EDIT REQUEST
    // =====================================

    @GetMapping("/edit/{id}")
    public String editLabRequest(
            @PathVariable Long id,
            Model model
    ) {



        PatientLabRequest request =
                labRequestService
                .getLabRequestById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Lab Request Not Found"
                        )
                );





        model.addAttribute(
                "labRequest",
                request
        );



        return "edit-lab-request";

    }








    // =====================================
    // UPDATE REQUEST
    // =====================================

    @PostMapping("/update")
    public String updateLabRequest(
            @ModelAttribute PatientLabRequest request
    ) {



        labRequestService
                .updateLabRequest(
                        request
                );



        return "redirect:/admin/lab-request";

    }








    // =====================================
    // DELETE REQUEST
    // =====================================

    @GetMapping("/delete/{id}")
    public String deleteLabRequest(
            @PathVariable Long id
    ) {



        labRequestService
                .deleteLabRequest(id);



        return "redirect:/admin/lab-request";

    }








    // =====================================
    // SEARCH PATIENT REQUEST
    // =====================================

    @GetMapping("/search")
    public String searchLabRequest(
            @RequestParam String keyword,
            Model model
    ) {



        model.addAttribute(
                "requests",
                labRequestService
                .searchByPatientName(keyword)
        );



        return "view-lab-requests";

    }








    // =====================================
    // PENDING REQUEST QUEUE
    // =====================================

    @GetMapping("/pending")
    public String pendingRequests(
            Model model
    ) {



        model.addAttribute(
                "requests",
                labRequestService
                .getPendingRequests()
        );



        return "view-lab-requests";

    }








    // =====================================
    // COMPLETED REQUEST QUEUE
    // =====================================

    @GetMapping("/completed")
    public String completedRequests(
            Model model
    ) {



        model.addAttribute(
                "requests",
                labRequestService
                .getCompletedRequests()
        );



        return "view-lab-requests";

    }








    // =====================================
    // LAB REQUEST DASHBOARD
    // =====================================

    @GetMapping("/dashboard")
    public String labRequestDashboard(
            Model model
    ) {



        model.addAttribute(
                "totalRequests",
                labRequestService
                .getTotalRequests()
        );



        model.addAttribute(
                "pendingRequests",
                labRequestService
                .getPendingCount()
        );



        model.addAttribute(
                "completedRequests",
                labRequestService
                .getCompletedCount()
        );



        return "lab-request-dashboard";

    }


}
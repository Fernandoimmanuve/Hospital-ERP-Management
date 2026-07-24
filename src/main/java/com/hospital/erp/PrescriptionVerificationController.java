package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;



@Controller
@RequestMapping("/admin/pharmacy/prescription")
public class PrescriptionVerificationController {



    private final PrescriptionVerificationService prescriptionService;

    private final PharmacyStockService pharmacyStockService;




public PrescriptionVerificationController(
        PrescriptionVerificationService prescriptionService,
        PharmacyStockService pharmacyStockService
) {

    this.prescriptionService = prescriptionService;

    this.pharmacyStockService = pharmacyStockService;

}






    // =====================================
    // VIEW ALL PRESCRIPTIONS
    // =====================================

    @GetMapping
    public String viewPrescriptions(Model model) {


        model.addAttribute(
                "prescriptions",
                prescriptionService
                .getAllPrescriptionVerifications()
        );


        return "view-prescription-verification";

    }







    // =====================================
    // OPEN ADD VERIFICATION PAGE
    // =====================================

    @GetMapping("/add")
    public String addPrescriptionPage(
            Model model
    ) {


        PrescriptionVerification prescription =
                new PrescriptionVerification();


        prescription.setVerificationStatus(
                "Pending"
        );


        prescription.setVerifiedDate(
                LocalDate.now()
        );


        model.addAttribute(
                "prescription",
                prescription
        );


        return "add-prescription-verification";

    }







    // =====================================
    // SAVE PRESCRIPTION VERIFICATION
    // =====================================

    @PostMapping("/save")
    public String savePrescription(
            @ModelAttribute PrescriptionVerification prescription
    ) {


        if(prescription.getVerificationStatus()==null)
        {

            prescription.setVerificationStatus(
                    "Pending"
            );

        }



        if(prescription.getVerifiedDate()==null)
        {

            prescription.setVerifiedDate(
                    LocalDate.now()
            );

        }



        prescriptionService
                .savePrescriptionVerification(
                        prescription
                );



        return "redirect:/admin/pharmacy/prescription";

    }







    // =====================================
    // PENDING PRESCRIPTIONS
    // =====================================

    @GetMapping("/pending")
    public String pendingPrescriptions(
            Model model
    ) {


        model.addAttribute(
                "prescriptions",
                prescriptionService
                .getPendingPrescriptions()
        );


        return "view-prescription-verification";

    }







    // =====================================
    // APPROVE PRESCRIPTION
    // =====================================

    @GetMapping("/approve/{id}")
public String approvePrescription(
        @PathVariable Long id,
        Model model
) {



    PrescriptionVerification prescription =
            prescriptionService
            .getPrescriptionVerificationById(id)
            .orElseThrow(
                    () -> new RuntimeException(
                            "Prescription Not Found"
                    )
            );





    boolean stockAvailable =
            pharmacyStockService
            .issueMedicine(
                    prescription.getMedicineName(),
                    prescription.getQuantity(),
                    prescription.getPrescriptionId(),
                    prescription.getPharmacistName()
            );






    if(!stockAvailable)
    {


        model.addAttribute(
                "error",
                "Medicine stock not available"
        );


        model.addAttribute(
                "prescriptions",
                prescriptionService
                .getAllPrescriptionVerifications()
        );


        return "view-prescription-verification";


    }







    prescription.setVerificationStatus(
            "DISPENSED"
    );



    prescription.setVerifiedDate(
            java.time.LocalDate.now()
    );





    prescriptionService
            .updatePrescriptionVerification(
                    prescription
            );





    return "redirect:/admin/pharmacy/prescription";

}








    // =====================================
    // REJECT PRESCRIPTION
    // =====================================

    @GetMapping("/reject/{id}")
    public String rejectPrescription(
            @PathVariable Long id
    ) {



        PrescriptionVerification prescription =
                prescriptionService
                .getPrescriptionVerificationById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Prescription Not Found"
                        )
                );



        prescription.setVerificationStatus(
                "Rejected"
        );



        prescription.setVerifiedDate(
                LocalDate.now()
        );



        prescriptionService
                .updatePrescriptionVerification(
                        prescription
                );



        return "redirect:/admin/pharmacy/prescription";

    }







    // =====================================
    // SEARCH BY PATIENT
    // =====================================

    @GetMapping("/search")
    public String searchPrescription(
            @RequestParam String keyword,
            Model model
    ) {



        model.addAttribute(
                "prescriptions",
                prescriptionService
                .searchByPatientName(keyword)
        );



        return "view-prescription-verification";

    }








    // =====================================
    // DELETE PRESCRIPTION
    // =====================================

    @GetMapping("/delete/{id}")
    public String deletePrescription(
            @PathVariable Long id
    ) {


        prescriptionService
                .deletePrescriptionVerification(id);



        return "redirect:/admin/pharmacy/prescription";

    }







    // =====================================
    // PHARMACY PRESCRIPTION DASHBOARD
    // =====================================

    @GetMapping("/dashboard")
    public String prescriptionDashboard(
            Model model
    ) {


        model.addAttribute(
                "total",
                prescriptionService
                .getTotalPrescriptionCount()
        );


        model.addAttribute(
                "pending",
                prescriptionService
                .getPendingCount()
        );


        model.addAttribute(
                "approved",
                prescriptionService
                .getApprovedCount()
        );


        model.addAttribute(
                "rejected",
                prescriptionService
                .getRejectedCount()
        );



        return "prescription-dashboard";

    }


}
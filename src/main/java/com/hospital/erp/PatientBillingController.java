package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/patient/billing")
public class PatientBillingController {


    private final BillService billService;


    public PatientBillingController(BillService billService) {

        this.billService = billService;

    }



    // =====================================
    // VIEW PATIENT BILLS
    // =====================================

    @GetMapping
    public String viewBills(Model model) {


        // Temporary patient ID
        // Replace later with logged-in patient ID

        Long patientId = 1L;



        List<Bill> bills =
                billService.getBillsByPatientId(patientId);



        model.addAttribute(
                "bills",
                bills
        );



        return "billing";
    }





    // =====================================
    // VIEW BILL DETAILS
    // =====================================

    @GetMapping("/view/{id}")
    public String viewBillDetails(
            @PathVariable Long id,
            Model model) {


        Bill bill =
                billService.getBillById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Bill Not Found"
                        ));



        model.addAttribute(
                "bill",
                bill
        );



        return "bill-details";
    }





    // =====================================
    // PAYMENT PAGE
    // =====================================

    @GetMapping("/pay/{id}")
    public String paymentPage(
            @PathVariable Long id,
            Model model) {


        Bill bill =
                billService.getBillById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Bill Not Found"
                        ));



        model.addAttribute(
                "bill",
                bill
        );



        return "payment";
    }


}
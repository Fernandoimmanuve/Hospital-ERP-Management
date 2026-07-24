package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/billing")
public class AdminBillingController {

    private final BillService billService;

    public AdminBillingController(BillService billService) {
        this.billService = billService;
    }

    // =====================================
    // VIEW ALL BILLS
    // =====================================
    @GetMapping
    public String viewAllBills(Model model) {
        model.addAttribute("bills", billService.getAllBills());
        model.addAttribute("totalBills", billService.getTotalBills());
        model.addAttribute("paidBills", billService.getPaidBills());
        model.addAttribute("pendingBills", billService.getPendingBills());
        return "admin_billing";
    }

    // =====================================
    // VIEW BILL DETAILS
    // =====================================
    @GetMapping("/view/{id}")
    public String viewBillDetails(@PathVariable Long id, Model model) {
        Bill bill = billService.getBillById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
        model.addAttribute("bill", bill);
        return "bill-details";
    }
}


package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/pharmacy/stock")
public class PharmacyStockController {

    private final PharmacyStockService pharmacyStockService;
    private final MedicineStockTransactionService transactionService;
    private final MedicineService medicineService;

    public PharmacyStockController(
            PharmacyStockService pharmacyStockService,
            MedicineStockTransactionService transactionService,
            MedicineService medicineService) {

        this.pharmacyStockService = pharmacyStockService;
        this.transactionService = transactionService;
        this.medicineService = medicineService;
    }

    // =====================================
    // DASHBOARD
    // =====================================

    @GetMapping
    public String dashboard(Model model) {

        model.addAttribute(
                "transactions",
                transactionService.getAllTransactions());

        model.addAttribute(
                "totalTransactions",
                transactionService.getTotalTransactions());

        model.addAttribute(
                "issueCount",
                transactionService.getIssueCount());

        model.addAttribute(
                "purchaseCount",
                transactionService.getPurchaseCount());

        return "stock-dashboard";
    }

    // =====================================
    // ISSUE MEDICINE PAGE
    // =====================================

    @GetMapping("/issue")
    public String issuePage(Model model) {

        model.addAttribute(
                "medicines",
                medicineService.getAllMedicines());

        return "add-medicine";
    }

    // =====================================
    // ISSUE MEDICINE
    // =====================================

    @PostMapping("/issue")
    public String issueMedicine(

            @RequestParam String medicineName,

            @RequestParam Integer quantity,

            @RequestParam(required = false)
            Long prescriptionId,

            @RequestParam
            String pharmacistName,

            Model model) {

        boolean success =
                pharmacyStockService.issueMedicine(
                        medicineName,
                        quantity,
                        prescriptionId,
                        pharmacistName);

        if (!success) {

            model.addAttribute(
                    "error",
                    "Medicine not available or insufficient stock.");

            model.addAttribute(
                    "medicines",
                    medicineService.getAllMedicines());

            return "add-medicine";
        }

        return "redirect:/admin/pharmacy/stock";
    }

    // =====================================
    // TRANSACTION HISTORY
    // =====================================

    @GetMapping("/history")
    public String history(Model model) {

        model.addAttribute(
                "transactions",
                transactionService.getAllTransactions());

        return "view-stock-transactions";
    }

    // =====================================
    // SEARCH MEDICINE
    // =====================================

    @GetMapping("/search")
    public String searchMedicine(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute(
                "transactions",
                transactionService.searchMedicine(keyword));

        return "view-stock-transactions";
    }

    // =====================================
    // ISSUE HISTORY
    // =====================================

    @GetMapping("/issue-history")
    public String issueHistory(Model model) {

        model.addAttribute(
                "transactions",
                transactionService.getIssueTransactions());

        return "view-stock-transactions";
    }

    // =====================================
    // PURCHASE HISTORY
    // =====================================

    @GetMapping("/purchase-history")
    public String purchaseHistory(Model model) {

        model.addAttribute(
                "transactions",
                transactionService.getPurchaseTransactions());

        return "view-stock-transactions";
    }
}
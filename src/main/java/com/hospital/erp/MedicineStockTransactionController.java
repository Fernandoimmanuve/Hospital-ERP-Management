package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/pharmacy/transactions")
public class MedicineStockTransactionController {

    private final MedicineStockTransactionService stockService;

    public MedicineStockTransactionController(
            MedicineStockTransactionService stockService) {

        this.stockService = stockService;
    }

    // =====================================
    // VIEW ALL STOCK TRANSACTIONS
    // =====================================

    @GetMapping
    public String viewStockTransactions(Model model) {

        model.addAttribute(
                "transactions",
                stockService.getAllTransactions());

        return "view-stock-transactions";
    }

    // =====================================
    // ISSUE HISTORY
    // =====================================

    @GetMapping("/issue")
    public String issueTransactions(Model model) {

        model.addAttribute(
                "transactions",
                stockService.getIssueTransactions());

        return "view-stock-transactions";
    }

    // =====================================
    // PURCHASE / RESTOCK HISTORY
    // =====================================

    @GetMapping("/restock")
    public String restockTransactions(Model model) {

        model.addAttribute(
                "transactions",
                stockService.getPurchaseTransactions());

        return "view-stock-transactions";
    }

    // =====================================
    // SEARCH MEDICINE
    // =====================================

    @GetMapping("/search")
    public String searchStock(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute(
                "transactions",
                stockService.searchMedicine(keyword));

        return "view-stock-transactions";
    }

    // =====================================
    // PRESCRIPTION HISTORY
    // =====================================

    @GetMapping("/prescription/{id}")
    public String prescriptionStockHistory(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "transactions",
                stockService.getTransactionsByPrescription(id));

        return "view-stock-transactions";
    }

    // =====================================
    // DASHBOARD
    // =====================================

    @GetMapping("/dashboard")
    public String stockDashboard(Model model) {

        model.addAttribute(
                "totalTransactions",
                stockService.getTotalTransactions());

        model.addAttribute(
                "issueCount",
                stockService.getIssueCount());

        model.addAttribute(
                "purchaseCount",
                stockService.getPurchaseCount());

        return "stock-dashboard";
    }
}
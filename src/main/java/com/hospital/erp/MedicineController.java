package com.hospital.erp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/pharmacy")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    // ===============================
    // VIEW ALL MEDICINES
    // ===============================
    @GetMapping
    public String viewMedicines(Model model) {

        model.addAttribute("medicines",
                medicineService.getAllMedicines());

        return "view-medicine";
    }

    // ===============================
    // ADD MEDICINE PAGE
    // ===============================
    @GetMapping("/add")
    public String showAddMedicineForm(Model model) {

        model.addAttribute("medicine", new Medicine());

        return "add-medicine";
    }

    // ===============================
    // SAVE MEDICINE
    // ===============================
    @PostMapping("/save")
    public String saveMedicine(@ModelAttribute Medicine medicine,
                               Model model) {

        if (medicineService.existsByBatchNumber(medicine.getBatchNumber())) {

            model.addAttribute("error",
                    "Medicine batch number already exists");

            model.addAttribute("medicine", medicine);

            return "add-medicine";
        }

        medicineService.saveMedicine(medicine);

        return "redirect:/admin/pharmacy";
    }

    // ===============================
    // EDIT MEDICINE
    // ===============================
    @GetMapping("/edit/{id}")
    public String editMedicine(@PathVariable Long id,
                               Model model) {

        Medicine medicine = medicineService.getMedicineById(id)
                .orElseThrow(() ->
                        new RuntimeException("Medicine Not Found"));

        model.addAttribute("medicine", medicine);

        return "edit-medicine";
    }

    // ===============================
    // UPDATE MEDICINE
    // ===============================
    @PostMapping("/update")
    public String updateMedicine(@ModelAttribute Medicine medicine) {

        medicineService.updateMedicine(medicine);

        return "redirect:/admin/pharmacy";
    }

    // ===============================
    // DELETE MEDICINE
    // ===============================
    @GetMapping("/delete/{id}")
    public String deleteMedicine(@PathVariable Long id) {

        medicineService.deleteMedicine(id);

        return "redirect:/admin/pharmacy";
    }

    // ===============================
    // SEARCH MEDICINE
    // ===============================
    @GetMapping("/search")
    public String searchMedicine(@RequestParam String keyword,
                                 Model model) {

        model.addAttribute("medicines",
                medicineService.searchByMedicineName(keyword));

        return "view-medicine";
    }

    // ===============================
    // FILTER CATEGORY
    // ===============================
    @GetMapping("/category/{category}")
    public String filterCategory(@PathVariable String category,
                                 Model model) {

        model.addAttribute("medicines",
                medicineService.searchByCategory(category));

        return "view-medicine";
    }

    // ===============================
    // AVAILABLE MEDICINES
    // ===============================
    @GetMapping("/available")
    public String availableMedicines(Model model) {

        model.addAttribute("medicines",
                medicineService.getMedicinesByStatus("Available"));

        return "view-medicine";
    }

    // ===============================
    // EXPIRED MEDICINES
    // ===============================
    @GetMapping("/expired")
    public String expiredMedicines(Model model) {

        model.addAttribute("medicines",
                medicineService.getExpiredMedicines());

        return "view-medicine";
    }

    // ===============================
    // LOW STOCK
    // ===============================
    @GetMapping("/low-stock")
    public String lowStockMedicines(Model model) {

        model.addAttribute("medicines",
                medicineService.getLowStockMedicines(20));

        return "view-medicine";
    }

    // ===============================
    // DASHBOARD
    // ===============================
    @GetMapping("/dashboard")
    public String pharmacyDashboard(Model model) {

        model.addAttribute("totalMedicines",
                medicineService.getTotalMedicineCount());

        model.addAttribute("availableMedicines",
                medicineService.getAvailableMedicineCount());

        model.addAttribute("lowStock",
                medicineService.getLowStockCount());

        model.addAttribute("expiredMedicines",
                medicineService.getExpiredMedicineCount());

        return "stock-dashboard";
    }
}
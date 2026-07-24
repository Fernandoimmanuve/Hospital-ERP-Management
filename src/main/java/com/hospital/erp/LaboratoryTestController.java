package com.hospital.erp;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;



@Controller
@RequestMapping("/admin/laboratory-tests")
public class LaboratoryTestController {



    private final LaboratoryTestService laboratoryTestService;





    public LaboratoryTestController(
            LaboratoryTestService laboratoryTestService
    ) {


        this.laboratoryTestService = laboratoryTestService;

    }







    // =====================================
    // VIEW ALL LAB TESTS
    // =====================================

    @GetMapping
    public String viewLaboratoryTests(
            Model model
    ) {


        model.addAttribute(
                "tests",
                laboratoryTestService
                .getAllLaboratoryTests()
        );


        return "view-laboratory-tests";

    }







    // =====================================
    // OPEN ADD TEST PAGE
    // =====================================

    @GetMapping("/add")
    public String showAddTestForm(
            Model model
    ) {


        model.addAttribute(
                "laboratoryTest",
                new LaboratoryTest()
        );


        return "add-laboratory-test";

    }







    // =====================================
    // SAVE LAB TEST
    // =====================================

    @PostMapping("/save")
    public String saveLaboratoryTest(
            @ModelAttribute LaboratoryTest laboratoryTest,
            Model model
    ) {



        if(
            laboratoryTestService
            .existsByTestName(
                    laboratoryTest.getTestName()
            )
        )
        {


            model.addAttribute(
                    "error",
                    "Laboratory test already exists"
            );


            model.addAttribute(
                    "laboratoryTest",
                    laboratoryTest
            );


            return "add-laboratory-test";

        }





        laboratoryTest.setCreatedDate(
                LocalDate.now()
        );



        if(laboratoryTest.getStatus()==null)
        {

            laboratoryTest.setStatus(
                    "Active"
            );

        }





        laboratoryTestService
                .saveLaboratoryTest(
                        laboratoryTest
                );




        return "redirect:/admin/laboratory-tests";

    }







    // =====================================
    // EDIT LAB TEST
    // =====================================

    @GetMapping("/edit/{id}")
    public String editLaboratoryTest(
            @PathVariable Long id,
            Model model
    ) {



        LaboratoryTest test =
                laboratoryTestService
                .getLaboratoryTestById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Laboratory Test Not Found"
                        )
                );





        model.addAttribute(
                "laboratoryTest",
                test
        );




        return "edit-laboratory-test";

    }







    // =====================================
    // UPDATE LAB TEST
    // =====================================

    @PostMapping("/update")
    public String updateLaboratoryTest(
            @ModelAttribute LaboratoryTest laboratoryTest
    ) {



        laboratoryTestService
                .updateLaboratoryTest(
                        laboratoryTest
                );



        return "redirect:/admin/laboratory-tests";

    }







    // =====================================
    // DELETE LAB TEST
    // =====================================

    @GetMapping("/delete/{id}")
    public String deleteLaboratoryTest(
            @PathVariable Long id
    ) {



        laboratoryTestService
                .deleteLaboratoryTest(id);



        return "redirect:/admin/laboratory-tests";

    }







    // =====================================
    // SEARCH LAB TEST
    // =====================================

    @GetMapping("/search")
    public String searchLaboratoryTest(
            @RequestParam String keyword,
            Model model
    ) {



        model.addAttribute(
                "tests",
                laboratoryTestService
                .searchByTestName(keyword)
        );



        return "view-laboratory-tests";

    }







    // =====================================
    // ACTIVE TEST LIST
    // =====================================

    @GetMapping("/active")
    public String activeTests(
            Model model
    ) {



        model.addAttribute(
                "tests",
                laboratoryTestService
                .getActiveTests()
        );



        return "view-laboratory-tests";

    }







    // =====================================
    // LAB DASHBOARD
    // =====================================

    @GetMapping("/dashboard")
    public String laboratoryDashboard(
            Model model
    ) {



        model.addAttribute(
                "totalTests",
                laboratoryTestService
                .getTotalTests()
        );



        model.addAttribute(
                "activeTests",
                laboratoryTestService
                .getActiveTestCount()
        );



        return "laboratory-dashboard";

    }


}

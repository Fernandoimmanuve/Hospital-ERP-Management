package com.hospital.erp;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.*;



import java.util.List;



@Controller
@RequestMapping("/patient/feedback")
public class PatientFeedbackController {




    private final FeedbackService feedbackService;







    public PatientFeedbackController(
            FeedbackService feedbackService
    ){

        this.feedbackService = feedbackService;

    }









    // =====================================
    // SHOW FEEDBACK FORM
    // =====================================


    @GetMapping("/add")
    public String feedbackForm(
            Model model
    ){



        Feedback feedback = new Feedback();



        model.addAttribute(
                "feedback",
                feedback
        );



        return "add-feedback";

    }









    // =====================================
    // SAVE FEEDBACK
    // =====================================


    @PostMapping("/save")
    public String saveFeedback(
            @ModelAttribute Feedback feedback
    ){



        // Temporary patient id

        feedback.setPatientId(
                1L
        );




        feedbackService
                .saveFeedback(
                        feedback
                );



        return "redirect:/patient/feedback";

    }









    // =====================================
    // VIEW PATIENT FEEDBACK
    // =====================================


    @GetMapping
    public String viewFeedback(
            Model model
    ){



        Long patientId = 1L;





        List<Feedback> feedbackList =
                feedbackService
                .getPatientFeedback(
                        patientId
                );





        model.addAttribute(
                "feedbackList",
                feedbackList
        );



        return "feedback-list";

    }









    // =====================================
    // EDIT FEEDBACK PAGE
    // =====================================


    @GetMapping("/edit/{id}")
    public String editFeedback(
            @PathVariable Long id,
            Model model
    ){



        Feedback feedback =
                feedbackService
                .getFeedbackById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Feedback Not Found"
                    )
                );





        model.addAttribute(
                "feedback",
                feedback
        );



        return "edit-feedback";

    }









    // =====================================
    // UPDATE FEEDBACK
    // =====================================


    @PostMapping("/update")
    public String updateFeedback(
            @ModelAttribute Feedback feedback
    ){



        feedbackService
                .updateFeedback(
                        feedback
                );



        return "redirect:/patient/feedback";

    }









    // =====================================
    // DELETE FEEDBACK
    // =====================================


    @GetMapping("/delete/{id}")
    public String deleteFeedback(
            @PathVariable Long id
    ){



        feedbackService
                .deleteFeedback(
                        id
                );



        return "redirect:/patient/feedback";

    }





}
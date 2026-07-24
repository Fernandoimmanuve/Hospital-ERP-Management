package com.hospital.erp;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.*;



import java.util.List;



@Controller
@RequestMapping("/patient/notifications")
public class PatientNotificationController {



    private final NotificationService notificationService;





    public PatientNotificationController(
            NotificationService notificationService
    ){

        this.notificationService = notificationService;

    }









    // =====================================
    // VIEW ALL NOTIFICATIONS
    // =====================================


    @GetMapping
    public String viewNotifications(
            Model model
    ){



        // Temporary patient id

        Long patientId = 1L;




        List<Notification> notifications =
                notificationService
                .getPatientNotifications(
                        patientId
                );




        long unreadCount =
                notificationService
                .getUnreadCount(
                        patientId
                );




        model.addAttribute(
                "notifications",
                notifications
        );



        model.addAttribute(
                "unreadCount",
                unreadCount
        );



        return "notifications";

    }









    // =====================================
    // VIEW NOTIFICATION DETAILS
    // =====================================


    @GetMapping("/view/{id}")
    public String viewNotification(
            @PathVariable Long id,
            Model model
    ){



        Notification notification =
                notificationService
                .getNotificationById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Notification Not Found"
                    )
                );



        model.addAttribute(
                "notification",
                notification
        );



        return "notification-details";

    }









    // =====================================
    // MARK AS READ
    // =====================================


    @GetMapping("/read/{id}")
    public String markAsRead(
            @PathVariable Long id
    ){



        notificationService
                .markAsRead(
                        id
                );



        return "redirect:/patient/notifications";

    }









    // =====================================
    // DELETE NOTIFICATION
    // =====================================


    @GetMapping("/delete/{id}")
    public String deleteNotification(
            @PathVariable Long id
    ){



        notificationService
                .deleteNotification(
                        id
                );



        return "redirect:/patient/notifications";

    }






}
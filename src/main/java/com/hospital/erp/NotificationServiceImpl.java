package com.hospital.erp;




import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@Service
public class NotificationServiceImpl 
        implements NotificationService {



    private final NotificationRepository notificationRepository;





    public NotificationServiceImpl(
            NotificationRepository notificationRepository
    ){

        this.notificationRepository = notificationRepository;

    }








    // =====================================
    // SAVE NOTIFICATION
    // =====================================


    @Override
    public Notification saveNotification(
            Notification notification
    ){


        if(notification.getCreatedAt()==null){

            notification.setCreatedAt(
                    LocalDateTime.now()
            );

        }



        if(notification.getStatus()==null){

            notification.setStatus(
                    "UNREAD"
            );

        }



        return notificationRepository.save(
                notification
        );

    }









    // =====================================
    // GET ALL PATIENT NOTIFICATIONS
    // =====================================


    @Override
    public List<Notification> getPatientNotifications(
            Long patientId
    ){


        return notificationRepository
                .findByPatientIdOrderByCreatedAtDesc(
                        patientId
                );

    }









    // =====================================
    // GET UNREAD NOTIFICATIONS
    // =====================================


    @Override
    public List<Notification> getUnreadNotifications(
            Long patientId
    ){


        return notificationRepository
                .findByPatientIdAndStatusOrderByCreatedAtDesc(
                        patientId,
                        "UNREAD"
                );

    }









    // =====================================
    // COUNT UNREAD NOTIFICATIONS
    // =====================================


    @Override
    public long getUnreadCount(
            Long patientId
    ){


        return notificationRepository
                .countByPatientIdAndStatus(
                        patientId,
                        "UNREAD"
                );

    }









    // =====================================
    // GET NOTIFICATION BY ID
    // =====================================


    @Override
    public Optional<Notification> getNotificationById(
            Long id
    ){


        return notificationRepository
                .findById(id);

    }









    // =====================================
    // MARK AS READ
    // =====================================


    @Override
    public Notification markAsRead(
            Long id
    ){



        Notification notification =
                notificationRepository
                .findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Notification Not Found"
                    )
                );



        notification.setStatus(
                "READ"
        );



        return notificationRepository.save(
                notification
        );

    }









    // =====================================
    // DELETE NOTIFICATION
    // =====================================


    @Override
    public void deleteNotification(
            Long id
    ){


        notificationRepository.deleteById(
                id
        );

    }




}
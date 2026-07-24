package com.hospital.erp;



import java.util.List;
import java.util.Optional;



public interface NotificationService {



    // Create notification

    Notification saveNotification(
            Notification notification
    );





    // Get patient notifications

    List<Notification> getPatientNotifications(
            Long patientId
    );






    // Get unread notifications

    List<Notification> getUnreadNotifications(
            Long patientId
    );







    // Count unread notifications

    long getUnreadCount(
            Long patientId
    );








    // Get notification by id

    Optional<Notification> getNotificationById(
            Long id
    );








    // Mark notification as read

    Notification markAsRead(
            Long id
    );








    // Delete notification

    void deleteNotification(
            Long id
    );



}
package com.hospital.erp;




import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;




public interface NotificationRepository 
        extends JpaRepository<Notification, Long> {




    // Get all notifications of a patient

    List<Notification> findByPatientIdOrderByCreatedAtDesc(
            Long patientId
    );






    // Get unread notifications

    List<Notification> findByPatientIdAndStatusOrderByCreatedAtDesc(
            Long patientId,
            String status
    );







    // Count unread notifications

    long countByPatientIdAndStatus(
            Long patientId,
            String status
    );





}
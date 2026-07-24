package com.hospital.erp;



import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@Service
public class FeedbackServiceImpl 
        implements FeedbackService {




    private final FeedbackRepository feedbackRepository;







    public FeedbackServiceImpl(
            FeedbackRepository feedbackRepository
    ){

        this.feedbackRepository = feedbackRepository;

    }









    // =====================================
    // SAVE FEEDBACK
    // =====================================


    @Override
    public Feedback saveFeedback(
            Feedback feedback
    ){



        if(feedback.getCreatedAt()==null){

            feedback.setCreatedAt(
                    LocalDateTime.now()
            );

        }




        if(feedback.getStatus()==null){

            feedback.setStatus(
                    "ACTIVE"
            );

        }



        return feedbackRepository.save(
                feedback
        );

    }









    // =====================================
    // GET FEEDBACK BY ID
    // =====================================


    @Override
    public Optional<Feedback> getFeedbackById(
            Long id
    ){


        return feedbackRepository
                .findById(id);

    }









    // =====================================
    // GET PATIENT FEEDBACK
    // =====================================


    @Override
    public List<Feedback> getPatientFeedback(
            Long patientId
    ){


        return feedbackRepository
                .findByPatientIdOrderByCreatedAtDesc(
                        patientId
                );

    }









    // =====================================
    // GET DOCTOR REVIEWS
    // =====================================


    @Override
    public List<Feedback> getDoctorReviews(
            Long doctorId
    ){


        return feedbackRepository
                .findByDoctorIdOrderByCreatedAtDesc(
                        doctorId
                );

    }









    // =====================================
    // GET SERVICE FEEDBACK
    // =====================================


    @Override
    public List<Feedback> getServiceFeedback(
            String serviceType
    ){


        return feedbackRepository
                .findByServiceType(
                        serviceType
                );

    }









    // =====================================
    // UPDATE FEEDBACK
    // =====================================


    @Override
    public Feedback updateFeedback(
            Feedback feedback
    ){


        return feedbackRepository.save(
                feedback
        );

    }









    // =====================================
    // DELETE FEEDBACK
    // =====================================


    @Override
    public void deleteFeedback(
            Long id
    ){


        feedbackRepository.deleteById(
                id
        );

    }





}
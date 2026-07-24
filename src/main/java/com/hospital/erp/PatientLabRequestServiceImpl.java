package com.hospital.erp;



import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;



@Service
public class PatientLabRequestServiceImpl
        implements PatientLabRequestService {



    private final PatientLabRequestRepository repository;





    public PatientLabRequestServiceImpl(
            PatientLabRequestRepository repository
    ) {


        this.repository = repository;

    }






    @Override
    public PatientLabRequest saveLabRequest(
            PatientLabRequest request
    ) {


        return repository.save(request);

    }






    @Override
    public PatientLabRequest updateLabRequest(
            PatientLabRequest request
    ) {


        return repository.save(request);

    }






    @Override
    public void deleteLabRequest(
            Long id
    ) {


        repository.deleteById(id);

    }






    @Override
    public Optional<PatientLabRequest> getLabRequestById(
            Long id
    ) {


        return repository.findById(id);

    }






    @Override
    public List<PatientLabRequest> getAllLabRequests()
    {


        return repository.findAll();

    }






    @Override
    public List<PatientLabRequest> getPatientRequests(
            Long patientId
    ) {


        return repository.findByPatientId(
                patientId
        );

    }






    @Override
    public List<PatientLabRequest> getDoctorRequests(
            Long doctorId
    ) {


        return repository.findByDoctorId(
                doctorId
        );

    }






    @Override
    public List<PatientLabRequest> getTestRequests(
            Long testId
    ) {


        return repository.findByTestId(
                testId
        );

    }






    @Override
    public List<PatientLabRequest> searchByPatientName(
            String patientName
    ) {


        return repository
                .findByPatientNameContainingIgnoreCase(
                        patientName
                );

    }






    @Override
    public List<PatientLabRequest> searchByTestName(
            String testName
    ) {


        return repository
                .findByTestNameContainingIgnoreCase(
                        testName
                );

    }






    @Override
    public List<PatientLabRequest> getPendingRequests()
    {


        return repository
                .findByStatus(
                        "Pending"
                );

    }






    @Override
    public List<PatientLabRequest> getProcessingRequests()
    {


        return repository
                .findByStatus(
                        "Processing"
                );

    }






    @Override
    public List<PatientLabRequest> getCompletedRequests()
    {


        return repository
                .findByStatus(
                        "Completed"
                );

    }






    @Override
    public List<PatientLabRequest> getUrgentRequests()
    {


        return repository
                .findByPriority(
                        "URGENT"
                );

    }






    @Override
    public long getTotalRequests()
    {


        return repository.count();

    }






    @Override
    public long getPendingCount()
    {


        return repository
                .countByStatus(
                        "Pending"
                );

    }






    @Override
    public long getCompletedCount()
    {


        return repository
                .countByStatus(
                        "Completed"
                );

    }



}
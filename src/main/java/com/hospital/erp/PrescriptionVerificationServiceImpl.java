package com.hospital.erp;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PrescriptionVerificationServiceImpl 
        implements PrescriptionVerificationService {


    private final PrescriptionVerificationRepository repository;



    public PrescriptionVerificationServiceImpl(
            PrescriptionVerificationRepository repository) {

        this.repository = repository;

    }




    @Override
    public List<PrescriptionVerification> getAllPrescriptionVerifications(){

        return repository.findAll();

    }




    @Override
    public Optional<PrescriptionVerification> 
    getPrescriptionVerificationById(Long id){

        return repository.findById(id);

    }




    @Override
    public PrescriptionVerification savePrescriptionVerification(
            PrescriptionVerification prescriptionVerification){

        return repository.save(prescriptionVerification);

    }




    @Override
    public PrescriptionVerification updatePrescriptionVerification(
            PrescriptionVerification prescriptionVerification){

        return repository.save(prescriptionVerification);

    }





    @Override
    public void deletePrescriptionVerification(Long id){

        repository.deleteById(id);

    }




    @Override
    public List<PrescriptionVerification> getByPatientId(Long patientId){

        return repository.findByPrescriptionId(patientId);

    }




    @Override
    public List<PrescriptionVerification> getByDoctorId(Long doctorId){

        return repository.findByDoctorNameContainingIgnoreCase(doctorId.toString());

    }




    @Override
    public List<PrescriptionVerification> getByStatus(String status){

        return repository.findByVerificationStatus(status);

    }





    @Override
    public List<PrescriptionVerification> getPendingPrescriptions(){

        return repository.findByVerificationStatusIgnoreCase("PENDING");

    }





    @Override
    public List<PrescriptionVerification> searchByPatientName(
            String name){

        return repository
                .findByPatientNameContainingIgnoreCase(name);

    }





    @Override
    public long getTotalPrescriptionCount(){

        return repository.count();

    }





    @Override
    public long getPendingCount(){

        return repository.countByVerificationStatus("PENDING");

    }





    @Override
    public long getApprovedCount(){

        return repository.countByVerificationStatus("APPROVED");

    }





    @Override
    public long getRejectedCount(){

        return repository.countByVerificationStatus("REJECTED");

    }


}
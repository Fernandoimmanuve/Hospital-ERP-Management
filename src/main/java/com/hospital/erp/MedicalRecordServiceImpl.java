package com.hospital.erp;



import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@Service
public class MedicalRecordServiceImpl 
        implements MedicalRecordService {




    private final MedicalRecordRepository medicalRecordRepository;






    public MedicalRecordServiceImpl(
            MedicalRecordRepository medicalRecordRepository
    ){

        this.medicalRecordRepository = medicalRecordRepository;

    }







    // =====================================
    // SAVE MEDICAL RECORD
    // =====================================


    @Override
    public PatientMedicalRecord saveRecord(
            PatientMedicalRecord record
    ){



        if(record.getVisitDate()==null){

            record.setVisitDate(
                    LocalDateTime.now()
            );

        }






        if(record.getStatus()==null){

            record.setStatus(
                    "COMPLETED"
            );

        }






        return medicalRecordRepository.save(
                record
        );

    }









    // =====================================
    // GET RECORD BY ID
    // =====================================


    @Override
    public Optional<PatientMedicalRecord> getRecordById(
            Long id
    ){


        return medicalRecordRepository
                .findById(id);

    }









    // =====================================
    // PATIENT HISTORY
    // =====================================


    @Override
    public List<PatientMedicalRecord> getPatientHistory(
            Long patientId
    ){



        return medicalRecordRepository
                .findByPatientIdOrderByVisitDateDesc(
                        patientId
                );

    }









    // =====================================
    // DOCTOR HISTORY
    // =====================================


    @Override
    public List<PatientMedicalRecord> getDoctorHistory(
            Long doctorId
    ){



        return medicalRecordRepository
                .findByDoctorIdOrderByVisitDateDesc(
                        doctorId
                );

    }









    // =====================================
    // SEARCH DIAGNOSIS
    // =====================================


    @Override
    public List<PatientMedicalRecord> searchDiagnosis(
            String diagnosis
    ){



        return medicalRecordRepository
                .findByDiagnosisContainingIgnoreCase(
                        diagnosis
                );

    }









    // =====================================
    // COMPLETED RECORDS
    // =====================================


    @Override
    public List<PatientMedicalRecord> getCompletedRecords(){




        return medicalRecordRepository
                .findByStatus(
                        "COMPLETED"
                );


    }









    // =====================================
    // UPDATE RECORD
    // =====================================


    @Override
    public PatientMedicalRecord updateRecord(
            PatientMedicalRecord record
    ){



        return medicalRecordRepository.save(
                record
        );

    }









    // =====================================
    // DELETE RECORD
    // =====================================


    @Override
    public void deleteRecord(
            Long id
    ){



        medicalRecordRepository
                .deleteById(
                        id
                );

    }





}
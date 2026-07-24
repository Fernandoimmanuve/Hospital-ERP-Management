package com.hospital.erp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {


    private final PatientRepository patientRepository;


    public PatientServiceImpl(PatientRepository patientRepository) {

        this.patientRepository = patientRepository;
    }



    @Override
    public Patient savePatient(Patient patient) {

        return patientRepository.save(patient);
    }



    @Override
    public Patient updatePatient(Patient patient) {

        return patientRepository.save(patient);
    }



    @Override
    public void deletePatient(Long id) {

        patientRepository.deleteById(id);
    }



    @Override
    public Optional<Patient> getPatientById(Long id) {

        return patientRepository.findById(id);
    }



    @Override
    public List<Patient> getAllPatients() {

        return patientRepository.findAll();
    }



    @Override
    public List<Patient> searchPatients(String keyword) {


        if(keyword == null || keyword.trim().isEmpty()) {

            return patientRepository.findAll();
        }


        return patientRepository
                .searchByKeyword(keyword);

    }



    @Override
    public long getPatientCount() {

        return patientRepository.count();
    }

}
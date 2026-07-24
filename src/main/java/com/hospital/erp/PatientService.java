package com.hospital.erp;

import java.util.List;
import java.util.Optional;

public interface PatientService {


    Patient savePatient(Patient patient);


    Patient updatePatient(Patient patient);


    void deletePatient(Long id);


    Optional<Patient> getPatientById(Long id);


    List<Patient> getAllPatients();


    List<Patient> searchPatients(String keyword);


    long getPatientCount();

}
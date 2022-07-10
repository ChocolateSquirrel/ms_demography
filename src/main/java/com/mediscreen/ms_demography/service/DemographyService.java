package com.mediscreen.ms_demography.service;

import com.mediscreen.ms_demography.exception.PatientNotFoundException;
import com.mediscreen.ms_demography.model.Patient;
import com.mediscreen.ms_demography.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemographyService {

    private final PatientRepository patientRepository;

    public DemographyService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient getPatient(int patientId) {
        return patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException(patientId));
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient updatePatient(int patientId, Patient patient) {
        Patient patientToUpdate = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException(patientId));

        patientToUpdate.setLastName(patient.getLastName());
        patientToUpdate.setFirstName(patient.getFirstName());
        patientToUpdate.setDateOfBirth(patient.getDateOfBirth());
        patientToUpdate.setSex(patient.getSex());
        patientToUpdate.setAddress(patient.getAddress());
        patientToUpdate.setPhone(patient.getPhone());

        patientRepository.save(patientToUpdate);
        return patientToUpdate;
    }

    public Patient deletePatient(int patientId){
        Patient patientToDelete = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException(patientId));
        patientRepository.delete(patientToDelete);
        return patientToDelete;
    }
}

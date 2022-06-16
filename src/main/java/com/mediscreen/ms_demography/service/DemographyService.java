package com.mediscreen.ms_demography.service;

import com.mediscreen.ms_demography.commandobject.UpdateForm;
import com.mediscreen.ms_demography.exception.PatientNotFoundException;
import com.mediscreen.ms_demography.model.Patient;
import com.mediscreen.ms_demography.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Patient updatePatient(UpdateForm updateForm) {
        int patientId = updateForm.getPatientId();
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException(patientId));
        String lastName = updateForm.getLastName();
        String firstName = updateForm.getFirstName();
        String dob = updateForm.getDateOfBirth();
        String sex = updateForm.getSex();
        String address = updateForm.getAddress();
        String phone = updateForm.getPhone();

        patient.setLastName(lastName);
        patient.setFirstName(firstName);
        patient.setDateOfBirth(dob);
        patient.setSex(sex);
        patient.setAddress(address);
        patient.setPhone(phone);

        patientRepository.save(patient);
        return patient;
    }
}

package com.mediscreen.ms_demography.controller;

import com.mediscreen.ms_demography.model.Patient;
import com.mediscreen.ms_demography.service.DemographyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patients")
public class DemographyController {

    private final DemographyService demographyService;

    public DemographyController(DemographyService demographyService) {
        this.demographyService = demographyService;
    }

    @GetMapping("/")
    public List<Patient> getAllPatients() {
        return demographyService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable(value = "id") int patientId) {
        return demographyService.getPatient(patientId);
    }

    @PostMapping("/")
    public Patient addPatient(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String dob, @RequestParam String sex,
                              @RequestParam String address, @RequestParam String phone){
        Patient patient = new Patient(firstName, lastName, dob, sex, address, phone);
        return demographyService.addPatient(patient);
    }

    @PutMapping("/{id}")
    public Patient updatePatientInfo(@PathVariable(value = "id") int patientId, @RequestBody Patient patient){
        Patient patientUpdated = demographyService.updatePatient(patientId, patient);
        return patientUpdated;
    }

    @DeleteMapping("/{id}")
    public Patient delete(@PathVariable(value = "id") int patientId) {
        Patient patientDeleted = demographyService.deletePatient(patientId);
        return patientDeleted;
    }



}

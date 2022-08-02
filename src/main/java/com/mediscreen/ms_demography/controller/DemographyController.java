package com.mediscreen.ms_demography.controller;

import com.mediscreen.ms_demography.model.Patient;
import com.mediscreen.ms_demography.model.Sex;
import com.mediscreen.ms_demography.service.DemographyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
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


    @PostMapping("/add")
    public Patient addPatient(@RequestBody Patient patient){
        return demographyService.addPatient(patient);
    }

    @PostMapping("/update/{id}")
    public Patient updatePatientInfo(@PathVariable(value = "id") int patientId, @RequestBody Patient patient){
        Patient patientUpdated = demographyService.updatePatient(patientId, patient);
        return patientUpdated;
    }

    @GetMapping("/delete/{id}")
    public Patient delete(@PathVariable(value = "id") int patientId) {
        Patient patientDeleted = demographyService.deletePatient(patientId);
        return patientDeleted;
    }



}

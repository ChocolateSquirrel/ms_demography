package com.mediscreen.ms_demography.controller;

import com.mediscreen.ms_demography.commandobject.UpdateForm;
import com.mediscreen.ms_demography.model.Patient;
import com.mediscreen.ms_demography.service.DemographyService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/view/patients")
public class DemographyViewController {

    private final DemographyService demographyService;

    public DemographyViewController(DemographyService demographyService) {
        this.demographyService = demographyService;
    }

    @GetMapping("/")
    public ModelAndView getAllPatients(Model model) {
        List<Patient> patientsList = demographyService.getAllPatients();
        model.addAttribute("patients", patientsList);
        return new ModelAndView("allPatientsPage");
    }

    @GetMapping("/{id}")
    public ModelAndView getPatient(@PathVariable(value = "id") int patientId, Model model) {
        Patient patient = demographyService.getPatient(patientId);
        model.addAttribute("patient", patient);
        return new ModelAndView("infoPatientPage");
    }

    @PostMapping("/")
    public Patient addPatient(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String dob, @RequestParam String sex,
                              @RequestParam String address, @RequestParam String phone){
        Patient patient = new Patient(firstName, lastName, dob, sex, address, phone);
        return demographyService.addPatient(patient);
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable(value = "id") int patientId, Model model){
        Patient patient = demographyService.getPatient(patientId);
        model.addAttribute("patient", patient);
        UpdateForm form = new UpdateForm(patientId, patient.getLastName(), patient.getFirstName(), patient.getDateOfBirth(), patient.getSex(), patient.getAddress(), patient.getPhone());
        return new ModelAndView("updateInfoPatientPage", "updateForm", form);
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

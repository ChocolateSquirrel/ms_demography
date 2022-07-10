package com.mediscreen.ms_demography.controller;

import com.mediscreen.ms_demography.commandobject.PatientForm;
import com.mediscreen.ms_demography.model.Patient;
import com.mediscreen.ms_demography.model.Sex;
import com.mediscreen.ms_demography.service.DemographyService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
        return new ModelAndView("home");
    }

    @GetMapping("/{id}")
    public ModelAndView getPatient(@PathVariable(value = "id") int patientId, Model model) {
        Patient patient = demographyService.getPatient(patientId);
        model.addAttribute("patient", patient);
        return new ModelAndView("infoPatientPage");
    }

    @GetMapping("/add")
    public ModelAndView showAddForm(Model model) {
        return new ModelAndView("addPatientForm", "form", new PatientForm());
    }

    @PostMapping("/")
    public ModelAndView addPatient(@Valid @ModelAttribute PatientForm form, Model model){
        demographyService.addPatient(new Patient(form.getFirstName(), form.getLastName(), form.getDateOfBirth(), form.getSex(), form.getAddress(), form.getPhone()));
        List<Patient> patientsList = demographyService.getAllPatients();
        model.addAttribute("patients", patientsList);
        return new ModelAndView("home");
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable(value = "id") int patientId, Model model){
        Patient patient = demographyService.getPatient(patientId);
        model.addAttribute("patient", patient);
        PatientForm form = new PatientForm(patientId, patient.getLastName(), patient.getFirstName(), patient.getDateOfBirth(), patient.getSex(), patient.getAddress(), patient.getPhone());
        return new ModelAndView("updateInfoPatientPage", "form", form);
    }

    @PostMapping("/update/{id}")
    public ModelAndView updatePatientInfo(@PathVariable(value = "id") int patientId, @Valid @ModelAttribute PatientForm form, Model model){
        Patient patientUpdated = demographyService.updatePatient(patientId, new Patient(form.getFirstName(), form.getLastName(), form.getDateOfBirth(), form.getSex(), form.getAddress(), form.getPhone()));
        List<Patient> patientsList = demographyService.getAllPatients();
        model.addAttribute("patients", patientsList);
        return new ModelAndView("home");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable(value = "id") int patientId, Model model) {
        demographyService.deletePatient(patientId);
        List<Patient> patientsList = demographyService.getAllPatients();
        model.addAttribute("patients", patientsList);
        return new ModelAndView("home");
    }
}

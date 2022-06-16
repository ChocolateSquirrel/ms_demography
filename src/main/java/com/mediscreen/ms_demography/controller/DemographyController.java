package com.mediscreen.ms_demography.controller;

import com.mediscreen.ms_demography.commandobject.UpdateForm;
import com.mediscreen.ms_demography.model.Patient;
import com.mediscreen.ms_demography.service.DemographyService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class DemographyController {

    private final DemographyService demographyService;

    public DemographyController(DemographyService demographyService) {
        this.demographyService = demographyService;
    }

    @GetMapping("/")
    public ModelAndView getAllPatients(Model model){
        List<Patient> patientsList = demographyService.getAllPatients();
        model.addAttribute("patients", patientsList);
        return new ModelAndView("allPatientsPage");
    }

    @PostMapping("/addPatient")
    public Patient addPatient(@RequestParam String prenom, @RequestParam String nom, @RequestParam String dob, @RequestParam String sex,
                              @RequestParam String address, @RequestParam String phone){
        Patient patient = new Patient(prenom, nom, dob, sex, address, phone);
        return demographyService.addPatient(patient);
    }

    @GetMapping("/getPatientInfo")
    public ModelAndView getPatientInfo(@RequestParam int patientId, Model model){
        Patient patient = demographyService.getPatient(patientId);
        model.addAttribute("patient", patient);
        return new ModelAndView("infoPage");
    }

    @GetMapping("/update")
    public ModelAndView showUpdateForm(@RequestParam int patientId, Model model){
        Patient patient = demographyService.getPatient(patientId);
        model.addAttribute("patient", patient);
        return new ModelAndView("updateInfoPage", "updateForm", new UpdateForm());
    }

    @PostMapping("/update")
    public ModelAndView updatePatientInfo(@ModelAttribute UpdateForm updateForm, Model model){
        Patient patient = demographyService.updatePatient(updateForm);
        model.addAttribute("patient", patient);
        return new ModelAndView("/", "updateForm", new UpdateForm());
    }


}

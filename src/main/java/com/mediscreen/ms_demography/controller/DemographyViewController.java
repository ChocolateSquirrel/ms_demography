package com.mediscreen.ms_demography.controller;

import com.mediscreen.ms_demography.model.Patient;
import com.mediscreen.ms_demography.service.DemographyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/view/patients")
public class DemographyViewController {

    private final DemographyService demographyService;

    public DemographyViewController(DemographyService demographyService) {
        this.demographyService = demographyService;
    }

    @GetMapping("/")
    public String getAllPatients(Model model) {
        log.info("Request GET : /view/patients/");
        List<Patient> patientsList = demographyService.getAllPatients();
        model.addAttribute("patients", patientsList);
        log.info("Response for /view/patients/ : there are " + patientsList.size() + " patients");
        return "home";
    }

    @GetMapping("/{id}")
    public String getPatient(@PathVariable(value = "id") int patientId, Model model) {
        log.info("Request GET : /view/patients/" + patientId);
        Patient patient = demographyService.getPatient(patientId);
        model.addAttribute("patient", patient);
        log.info("Response for /view/patients/" + patientId + " : " + patient.getFirstName() + " " + patient.getLastName());
        return "patient/info";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        log.info("Request GET : /view/patients/add/");
        model.addAttribute("patient", new Patient());
        log.info("Response : OK");
        return "patient/add";
    }

    @PostMapping("/")
    public String addPatient(@Valid @ModelAttribute Patient patient, Model model, BindingResult result){
        log.info("Request POST : /view/patients/");
        if (!result.hasErrors()){
            demographyService.addPatient(patient);
            List<Patient> patientsList = demographyService.getAllPatients();
            model.addAttribute("patients", patientsList);
            return "home";
        }
        log.error("Response : " + result.getErrorCount() + " errors");
        return "patient/add";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable(value = "id") int patientId, Model model){
        log.info("Request GET : /view/patients/update/" + patientId);
        Patient patient = demographyService.getPatient(patientId);
        model.addAttribute("patient", patient);
        log.info("Response for /view/patients/update/" + patientId + " : " + patient.getFirstName() + " " + patient.getLastName() + " is going to update");
        return "patient/update";
    }

    @PostMapping("/update/{id}")
    public String updatePatientInfo(@PathVariable(value = "id") int patientId, @Valid @ModelAttribute Patient patient, Model model, BindingResult result){
        log.info("Request POST : /view/patients/update/" + patientId);
        if (!result.hasErrors()){
            Patient patientUpdated = demographyService.updatePatient(patientId, patient);
            List<Patient> patientsList = demographyService.getAllPatients();
            model.addAttribute("patients", patientsList);
            return "home";
        }
        log.error("Response : " + result.getErrorCount() + " errors");
        return "patient/update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int patientId, Model model) {
        log.info("Request GET : /view/patients/delete/" + patientId);
        demographyService.deletePatient(patientId);
        List<Patient> patientsList = demographyService.getAllPatients();
        model.addAttribute("patients", patientsList);
        log.info("Response for /view/patients/update/" + patientId + " : there are " + patientsList.size() + " patients");
        return "home";
    }
}

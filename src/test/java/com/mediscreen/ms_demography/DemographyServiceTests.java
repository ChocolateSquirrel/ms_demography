package com.mediscreen.ms_demography;

import com.mediscreen.ms_demography.exception.PatientNotFoundException;
import com.mediscreen.ms_demography.model.Patient;
import com.mediscreen.ms_demography.model.Sex;
import com.mediscreen.ms_demography.service.DemographyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DemographyServiceTests {

    @Autowired
    private DemographyService demographyService;

    @Test
    public void addPatientTestOK(){
        int nbPatientsBefore = demographyService.getAllPatients().size();
        Patient patient = new Patient("Toto", "TOTO", "2022-07-22", Sex.FEMALE, "rue des Lilis", "0123456789");
        demographyService.addPatient(patient);
        int nbPatientsAfter = demographyService.getAllPatients().size();
        assertEquals(nbPatientsAfter, nbPatientsBefore + 1);
    }

    @Test
    public void addPatientTestNOK(){
        Patient patient = new Patient("", "TOTO", "2022-07-22", Sex.FEMALE, "rue des Lilis", "0123456789");
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> demographyService.addPatient(patient))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void getPatientOK(){
        Patient patient = demographyService.getPatient(2);
        assertEquals(patient.getFirstName(), "Test");
        assertEquals(patient.getLastName(), "TestBorderline");
        assertEquals(patient.getDateOfBirth(), "1945-06-24");
        assertEquals(patient.getSex(), Sex.MALE);
    }

    @Test
    public void getPatientNOK(){
        assertThrows(PatientNotFoundException.class, () -> demographyService.getPatient(8), "The patient 8 was not found in the database");
    }

    @Test
    public void updatePatientTestOK(){
        Patient patient = new Patient("Test","TestInDanger","2004-06-18",Sex.FEMALE, "3 Club Road", "300-444-5555");
        Patient patientAfterUpdate = demographyService.updatePatient(3, patient);
        assertEquals(patientAfterUpdate.getSex(), Sex.FEMALE);
    }

    @Test
    public void updatePatientTestNOK(){
        Patient patient = new Patient("Test","","2004-06-18",Sex.FEMALE, "3 Club Road", "300-444-5555");
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> demographyService.addPatient(patient))
                .isInstanceOf(ConstraintViolationException.class);
    }


    @Test
    public void deletePatientTestOK() {
        int nbPatientsBefore = demographyService.getAllPatients().size();
        demographyService.deletePatient(4);
        assertEquals(demographyService.getAllPatients().size(), nbPatientsBefore - 1);
        assertThrows(PatientNotFoundException.class, () -> demographyService.getPatient(4), "The patient 4 was not found in the database");
    }
}

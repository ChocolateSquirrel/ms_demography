package com.mediscreen.ms_demography;

import com.mediscreen.ms_demography.model.Patient;
import com.mediscreen.ms_demography.model.Sex;
import com.mediscreen.ms_demography.service.DemographyService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DemographyServiceTests {

    @Autowired
    private DemographyService demographyService;

    @Test
    public void patientTest(){
        Patient patient = new Patient("Toto", "TOTO", "2022-07-22", Sex.FEMALE, "rue des Lilis", "0123456789");

        // Save
        demographyService.addPatient(patient);
        Assertions.assertNotNull(patient.getPatientId());
        Assertions.assertEquals(demographyService.getAllPatients().size(), 1);


        // Update
        Patient patient2 = new Patient("Tata", "TATA", "2022-07-22", Sex.MALE, "rue des Lilis", "0123456789");
        demographyService.updatePatient(patient.getPatientId(), patient2);
        Assertions.assertEquals(patient.getFirstName(), "Tata");
        Assertions.assertEquals(patient.getLastName(), "TATA");
        Assertions.assertEquals(patient.getSex(), Sex.MALE);

        // Find
        Assertions.assertTrue(demographyService.getAllPatients().size() > 0);

        // Delete
        demographyService.deletePatient(patient.getPatientId());
        Assertions.assertEquals(demographyService.getAllPatients().size(), 0);
    }


}

package com.mediscreen.ms_demography.exception;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(int patientId) { //Entity
        super("The patient " + patientId + " was not found in the database");
    }

}

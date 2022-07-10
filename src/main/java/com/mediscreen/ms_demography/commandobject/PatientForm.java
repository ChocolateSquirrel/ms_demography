package com.mediscreen.ms_demography.commandobject;

import com.mediscreen.ms_demography.model.Sex;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class PatientForm {

    private int patientId;

    @NotBlank(message="lastName is mandatory")
    private String lastName;

    @NotBlank(message="firstName is mandatory")
    private String firstName;

    @NotBlank(message="date of birth is mandatory")
    //@Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$", message="invalid date of birth")
    private String dateOfBirth;

    @NotNull
    private Sex sex;

    private String address;

    private String phone;

    public PatientForm() {}

    public PatientForm(int patientId, String lastName, String firstName, String dateOfBirth, Sex sex, String address, String phone) {
        this.patientId = patientId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }
}
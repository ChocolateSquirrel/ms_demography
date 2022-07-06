package com.mediscreen.ms_demography.commandobject;

import lombok.Data;

@Data
public class UpdateForm {

    private int patientId;

    private String lastName;

    private String firstName;

    private String dateOfBirth;

    private String sex;

    private String address;

    private String phone;

    public UpdateForm() {}

    public UpdateForm(int patientId, String lastName, String firstName, String dateOfBirth, String sex, String address, String phone) {
        this.patientId = patientId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }
}
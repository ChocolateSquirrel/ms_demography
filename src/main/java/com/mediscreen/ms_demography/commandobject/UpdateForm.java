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
}

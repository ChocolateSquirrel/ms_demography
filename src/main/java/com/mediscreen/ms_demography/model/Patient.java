package com.mediscreen.ms_demography.model;

import lombok.Data;

import javax.persistence.*;
import javax.servlet.annotation.MultipartConfig;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Entity
@Table(name = "patient")
public class Patient {
    private static final String BIRTHDATE_PATTERN = "yyyy-MM-dd";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int patientId;

    @Column(name = "prenom")
    @NotBlank
    private String firstName;

    @Column(name = "nom")
    @NotBlank
    private String lastName;

    @Column(name = "dob")
    @NotBlank
    @Pattern(regexp = "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$")
    private String dateOfBirth;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Sex sex;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    public Patient() { }

    public Patient(String firstName, String lastName, String dateOfBirth, Sex sex, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }

}

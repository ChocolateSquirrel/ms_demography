package com.mediscreen.ms_demography.model;

import lombok.Data;

import javax.persistence.*;
import javax.servlet.annotation.MultipartConfig;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "patient")
public class Patient {

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

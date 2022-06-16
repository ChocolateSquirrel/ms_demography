package com.mediscreen.ms_demography.repository;

import com.mediscreen.ms_demography.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer>, JpaRepository<Patient, Integer> {

    public Optional<Patient> findById(@Param("patientId") int patientId);

    Patient findByLastName(String name);
}

package com.bueno.clinicalsapi.repos;

import com.bueno.clinicalsapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}

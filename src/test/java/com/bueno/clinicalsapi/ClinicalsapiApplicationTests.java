package com.bueno.clinicalsapi;

import com.bueno.clinicalsapi.model.Patient;
import com.bueno.clinicalsapi.repos.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class ClinicalsapiApplicationTests {

	@Autowired
	PatientRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	void getAllPatients() {
		List<Patient> patients = repository.findAll();
		patients.forEach(val -> {
			System.out.println(val.getFirstName());
		});

	}
}


package com.bueno.clinicalsapi.controllers;

import com.bueno.clinicalsapi.model.ClinicalData;
import com.bueno.clinicalsapi.model.Patient;
import com.bueno.clinicalsapi.repos.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.tools.jconsole.JConsole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PatientController {

    private PatientRepository repository;
    Map<String, String> filters = new HashMap<String, String>();

    @Autowired
    PatientController(PatientRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public List<Patient> getPatients() {
        return repository.findAll();
    }

    @RequestMapping(value = "/patients/{id}", method = RequestMethod.GET)
    public Patient getPatient(@PathVariable("id") int id) {
        return repository.findById(id).get();
    }

    @RequestMapping(value = "/patients", method = RequestMethod.POST)
    public Patient savePatient(@RequestBody Patient patient) {
        return repository.save(patient);
    }

    @RequestMapping(value = "/patients/analyze/{id}", method = RequestMethod.GET)
    public Patient analyze(@PathVariable("id") int id) {
        Patient patient = repository.findById(id).get();
        List<ClinicalData> clinicalData = patient.getClinicalData();
        List<ClinicalData> duplicatedClinicalData = new ArrayList<>(clinicalData);

//        for(ClinicalData eachEntry: duplicatedClinicalData){
        duplicatedClinicalData.stream().forEach(eachEntry->{
            if (filters.containsKey(eachEntry.getComponentName())) {
                clinicalData.remove(eachEntry);
            } else {
                filters.put(eachEntry.getComponentName(), null);
            }

            if (eachEntry.getComponentName().equals("hw")) {
                String[] heightAndWeight = eachEntry.getComponentValue().split("/");
                if (heightAndWeight != null && heightAndWeight.length > 1) {
                    float heightInMeters = Float.parseFloat(heightAndWeight[0]) * .4536F;
                    float bmi = Float.parseFloat(heightAndWeight[1]) / (heightInMeters * heightInMeters);
                    ClinicalData bmiData = new ClinicalData();
                    bmiData.setComponentName("bmi");
                    bmiData.setComponentValue(Float.toString(bmi));
                    clinicalData.add(bmiData);
                }
            }
        });
        filters.clear();
        return patient;
    }
}

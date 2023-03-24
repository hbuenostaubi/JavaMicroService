package com.bueno.clinicalsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "clinicaldata")
@JsonIgnoreProperties({"patient"}) //ignore patient with clinical data is serialized
public class ClinicalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String componentName;
    private String componentValue;
    private Timestamp measuredDateTime;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //validations happen if inject data w/o patient id
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnore
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentValue() {
        return componentValue;
    }

    public void setComponentValue(String componentValue) {
        this.componentValue = componentValue;
    }

    public Timestamp getMeasuredDateTime() {
        return measuredDateTime;
    }

    public void setMeasuredDateTime(Timestamp measuredDateTime) {
        this.measuredDateTime = measuredDateTime;
    }
}

package com.bueno.clinicalsapi.repos;

import com.bueno.clinicalsapi.model.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {
}

package com.pocosoft.drone.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pocosoft.drone.service.entity.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long>{
	

}

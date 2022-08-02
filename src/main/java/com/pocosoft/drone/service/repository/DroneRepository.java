package com.pocosoft.drone.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pocosoft.drone.service.entity.Drone;
import com.pocosoft.drone.service.entity.DroneState;

public interface DroneRepository extends JpaRepository<Drone, String>{

	public List<Drone> findByState(DroneState state);
	
	
	@Query(value = "SELECT * FROM Drone WHERE state = 'IDLE'  and BATTERY_CAPACITY  >= 50", 
			  nativeQuery = true)
			public List<Drone> getDronesAvailableForLoading();
	
	@Query(value = "SELECT * FROM Drone WHERE state = 'IDLE'  and BATTERY_CAPACITY  < 50", 
			  nativeQuery = true)
			public List<Drone> getDronesRequiringCharging();
}

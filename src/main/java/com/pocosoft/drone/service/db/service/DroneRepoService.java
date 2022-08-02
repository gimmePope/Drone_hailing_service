package com.pocosoft.drone.service.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pocosoft.drone.service.entity.Drone;
import com.pocosoft.drone.service.entity.DroneState;
import com.pocosoft.drone.service.repository.DroneRepository;

@Service
public class DroneRepoService {
	
	@Autowired
	private DroneRepository droneRepository;
	
	
	public Drone getDroneBySerialNumber(String serial)
	{
		return droneRepository.findById(serial).orElse(null);
	}
	
	public boolean checkIfDroneExist(String serial)
	{
		return droneRepository.existsById(serial);
	}
	
	public void updateDroneInfo(Drone d)
	{
		droneRepository.save(d);
	}
	
	public Drone registerDrone(Drone drone)
	{
		Drone registeredDrone = null;
		try
		{
			 registeredDrone = droneRepository.save(drone); 
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return registeredDrone;
	}
	
	public List<Drone> getDronesByState(DroneState state)
	{
		return droneRepository.findByState(state);
	}
	
	public List<Drone> getDroneAvailableForLoading()
	{
		return droneRepository.getDronesAvailableForLoading();
	}
	
	public List<Drone> getDronesForCharging()
	{
		return droneRepository.getDronesRequiringCharging();
	}

}

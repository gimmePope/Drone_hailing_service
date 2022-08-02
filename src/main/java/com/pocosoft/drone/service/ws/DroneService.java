package com.pocosoft.drone.service.ws;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pocosoft.drone.service.db.service.DroneRepoService;
import com.pocosoft.drone.service.entity.Drone;

@RestController
@RequestMapping(path = "/drone/service")
public class DroneService {
	
	@Autowired
	DroneRepoService droneRepoService;
	
	@PostMapping("/register")
	public ResponseEntity<String> Register(@Valid @RequestBody Drone aDrone, BindingResult bindResult)
	{
		System.out.println("ERRORS: " + bindResult.hasErrors());
		if (bindResult.hasErrors())
		{
			System.err.println("Error Found");
			return ResponseEntity.badRequest().body(bindResult.getFieldErrors().get(0).getDefaultMessage());
		}
		System.err.println("Validation OK , " + aDrone.toString());
		String sn = aDrone.getSerialNumber();
		
		boolean exist = droneRepoService.checkIfDroneExist(sn);
		System.err.println("Found: " + exist);
		if(exist)
		{
			return ResponseEntity.badRequest().body("Drone Serial Already Exist");
		}
		else
		{
			Drone savedDrone = droneRepoService.registerDrone(aDrone);
			if(savedDrone != null)
				return new ResponseEntity<String> ("Drone Successfully Registered", HttpStatus.CREATED);
					
		}
		
		return new ResponseEntity<String> ("Drone Registration Failed", HttpStatus.FAILED_DEPENDENCY);
	}
	
	
	@GetMapping("/check/battery/{serialNumber}")
	public ResponseEntity<Integer> getDroneBatteryCapacity(@PathVariable("serialNumber") String serialNumber)
	{
		if(droneRepoService.checkIfDroneExist(serialNumber))
			return  ResponseEntity.ok().body(droneRepoService.getDroneBySerialNumber(serialNumber).getBatteryCapacity());
		else
			return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping("/charge")
	public ResponseEntity<String> chargeDronesLowOnBattery()
	{
		List <Drone> lowBatteryDrones = droneRepoService.getDronesForCharging();
		
		if(lowBatteryDrones.size() < 1)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			lowBatteryDrones.stream().forEach(d -> {
				d.setBatteryCapacity(100);
				droneRepoService.updateDroneInfo(d);
			});
			
			return ResponseEntity.ok("All Low Battery Drones Charged");
		}
	}
	
	@GetMapping("/available/loading")
	public ResponseEntity<List<Drone>> getDronesAvailableForLoading()
	{
		List <Drone> availableDrones = droneRepoService.getDroneAvailableForLoading();
		
		if(availableDrones.size() < 1)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok(availableDrones);
		}
	}

}

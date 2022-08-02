package com.pocosoft.drone.service.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.pocosoft.drone.service.constrait.EnumNamePattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Drone {
	
	@Id
	@Size(min = 3, message = "Serial Number can't be less than 3 characters")
	@Size(max= 100, message = "Serial Number can't be more than 100 characters")
	//@GeneratedValue(generator = "uuid")
	//@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String serialNumber;
	@Enumerated(EnumType.STRING)
	@EnumNamePattern(regexp = "Lightweight|Middleweight|Cruiserweight|Heavyweight")
	private Model model;
	@Min(value=20, message = "Capacity cannot be lower than 20g")
	@Max(value = 500, message = "Capacity cannot be more than 500g")
	private int weightLimit;
	@Min(value=0, message = "Battery Capacity cannot be lower than 0")
	@Max(value = 100, message = "Battery Capacity cannot be more than 100")
	private int batteryCapacity;
	@Enumerated(EnumType.STRING)
	@EnumNamePattern(regexp = "IDLE|LOADING|LOADED|DELIVERING|DELIVERED|RETURNING")
	private DroneState state=DroneState.IDLE;

}

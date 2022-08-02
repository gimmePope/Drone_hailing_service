package com.pocosoft.drone.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity

public class Medication {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Pattern(regexp = "^[A-Za-z0-9_-]*$",message = "Invalid Medication Name")
	private String name;
	@NotNull
	@Pattern(regexp = "^[A-Z_-]*$",message = "Invalid Medication Code Supplied")
	private String code;
	@Min(value=2, message = "Medication weight cannot be lower than 2g")
	@Max(value = 50, message = "Medication weight cannot be more than 50g")
	private int weight;
	@Lob
	@Column(name="image", columnDefinition = "BLOB")
	private byte [] image;
	

}

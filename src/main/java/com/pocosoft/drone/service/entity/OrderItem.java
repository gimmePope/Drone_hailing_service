package com.pocosoft.drone.service.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@NoArgsConstructor
public class OrderItem {
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "medication",referencedColumnName="id")
	private Medication medication;
	private int quantity;
	
	
	

}

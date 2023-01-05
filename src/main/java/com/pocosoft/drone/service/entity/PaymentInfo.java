package com.pocosoft.drone.service.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import org.hibernate.annotations.GenericGenerator;

import com.pocosoft.drone.service.constrait.EnumNamePattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class PaymentInfo {
	
	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2",strategy = "org.hibernate.id.UUIDGenerator")
	private String paymentRef;
	
	@Enumerated(EnumType.STRING)
	@EnumNamePattern(regexp = "CASH|POS|TRANSFER|NQR")
	private PaymentType method;
	
	private String paymentName;
	private String paymentSource="Not Applicable";
	@DecimalMin(value="1.0", message = "Please Enter a valid Deposit Amount")
	private double amount;
	
	

}

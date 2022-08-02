package com.pocosoft.drone.service.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class DeliveryEvent {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long deliveryId;
	@ManyToOne
	@JoinColumn(name = "drone", referencedColumnName = "serialNumber",insertable = false)
	private Drone drone;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "orderId")
	private List<OrderEvent> orders;
	
	private DeliveryState state = DeliveryState.LOADING_ORDER;

}

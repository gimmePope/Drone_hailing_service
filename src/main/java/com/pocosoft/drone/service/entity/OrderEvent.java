package com.pocosoft.drone.service.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.pocosoft.drone.service.constrait.EnumNamePattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OrderEvent {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long orderId;
	private String customerName;
	private String address;
	private String areaCode;
	@Enumerated(EnumType.STRING)
	@EnumNamePattern(regexp = "RECEIVED|LOADED|DELIVERED")
	private OrderState state = OrderState.RECEIVED;
	@ElementCollection
	@CollectionTable(name="ORDEREVENT_ITEMS", joinColumns=@JoinColumn(name="ORDER_ID"))
	private List<OrderItem> items;
	@ManyToOne
	@JoinColumn(name="deliveryEvent",referencedColumnName = "deliveryId")
	private DeliveryEvent delivery;
	
	
	
	
	
	

}

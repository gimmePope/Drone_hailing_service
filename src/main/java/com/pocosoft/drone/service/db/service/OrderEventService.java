package com.pocosoft.drone.service.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pocosoft.drone.service.entity.OrderEvent;
import com.pocosoft.drone.service.entity.OrderItem;
import com.pocosoft.drone.service.entity.OrderState;
import com.pocosoft.drone.service.repository.OrderEventRepository;

@Service
public class OrderEventService {
	
	@Autowired
	OrderEventRepository orderEventRepo;
	
	public List<OrderEvent> getOrderEventByState(OrderState state)
	{
		List<OrderEvent> orders = orderEventRepo.findByState(state);
		//orders.get(0).getItems().get(0).getMedication();
		return orders;
	}
	
	public OrderEvent addOrderEvent(OrderEvent event)
	{
		return orderEventRepo.save(event);
	}
	
	
	public int getNetWeightOfOrder(List<OrderItem> items)
	{
		int netWeight = 0;
		
		for(OrderItem item : items)
		{
			netWeight = netWeight + (item.getQuantity() * item.getMedication().getWeight());
		}
		
		return netWeight;
	}
	

}

package com.pocosoft.drone.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pocosoft.drone.service.entity.OrderEvent;
import com.pocosoft.drone.service.entity.OrderState;

public interface OrderEventRepository extends JpaRepository<OrderEvent, Long>{

	
	public List<OrderEvent> findByState(OrderState state);
	
	
}

package com.pocosoft.drone.service.ws;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pocosoft.drone.service.db.service.OrderEventService;
import com.pocosoft.drone.service.entity.OrderEvent;

@RestController
@RequestMapping(path="/order/service")
public class OrderService {
	
	@Autowired
	OrderEventService orderEventService;
	
	@PostMapping("/register")
	public ResponseEntity<OrderEvent> addNewOrderEvent(@Valid @RequestBody OrderEvent event, BindingResult bindResult)
	{
		if (bindResult.hasErrors())
		{
			System.err.println("Error Found");
			System.out.println(bindResult.getFieldErrors().get(0).getDefaultMessage());
			return new ResponseEntity<OrderEvent>(HttpStatus.BAD_REQUEST);
		}
		//System.out.println(event.getItems().size() + " size found, " + event.getItems());
		
		
		OrderEvent saved =orderEventService.addOrderEvent(event);
	
		if(saved != null)
			return new ResponseEntity<OrderEvent>(saved, HttpStatus.CREATED);
		else
			return new ResponseEntity<OrderEvent>(HttpStatus.FAILED_DEPENDENCY);
		
	}

}

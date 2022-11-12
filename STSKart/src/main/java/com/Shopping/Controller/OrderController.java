package com.Shopping.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Shopping.Exception.CustomerException;
import com.Shopping.Exception.LoginException;
import com.Shopping.Exception.OrderException;
import com.Shopping.Model.Order;
import com.Shopping.Services.Order.OrderService;



@RestController
@RequestMapping(value = "/order")
public class OrderController {
	
	@Autowired
	OrderService order_service;
	
	@PostMapping()
	public ResponseEntity<Order> addOrderHandler(@RequestBody Order order, String key)throws CustomerException,LoginException{
		
		Order requestedOrder = order_service.addOrder(order, key);
		
		return new ResponseEntity<>(requestedOrder,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Order> updateOrderHandler(@RequestBody Order order, String key)throws OrderException,CustomerException,LoginException{
		
		return new ResponseEntity<>(order_service.updateOrder(order,key),HttpStatus.OK);
		
	}
	
	

}

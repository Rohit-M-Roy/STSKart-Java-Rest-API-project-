package com.Shopping.Services.Order;

import java.time.LocalDate;
import java.util.List;

import com.Shopping.Model.Order;

public interface OrderService {
	
	public Order addOrder(Order order);
	
	public Order updateOrder(Order order);
	
	public Order removeOrder(Order order);
	
	public Order viewOrder(Order order);
	
	public List<Order> viewAllOrders(LocalDate date);
	
	public List<Order> viewAllOrdersByLocation(String loc);
	
	public List<Order> viewAllOrdersByUserId(String userid);
	

}

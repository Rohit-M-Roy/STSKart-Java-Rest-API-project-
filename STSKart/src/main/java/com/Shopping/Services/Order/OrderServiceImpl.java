package com.Shopping.Services.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Shopping.Model.Customer;
import com.Shopping.Model.Order;
import com.Shopping.Repository.CustomerRepo;
import com.Shopping.Repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	CustomerRepo customer_repository;
	
	@Autowired
	OrderRepo order_repository;
	
	@Override
	public Order addOrder(Order order) {
		
		Order savedOrder = order;
		
		Customer customer_relatedTo_order = savedOrder.getCustomer();
		
		//to check if the customer exists in the database;
		
		Integer customerId = customer_relatedTo_order.getCustomerId();
		
		Customer fetchedCustomerDb = customer_repository.findById(customerId).orElseThrow();
		//throw no customer for the given id exists.
		
		order_repository.save(savedOrder);
		fetchedCustomerDb.getOrdersList().add(savedOrder);
		
		
		return savedOrder;
	}

	
	
	@Override
	public Order updateOrder(Order updateOrder) {
		
		Customer fetchedCustomerFromOrder = updateOrder.getCustomer();
		//check with db if that customer exists in database;
		
		List<Order> listOfOrders = fetchedCustomerFromOrder.getOrdersList();
		
		for(Order orderEle : listOfOrders) {
			
			if(orderEle.getOrderId() == updateOrder.getOrderId()) {
				
				orderEle.setOrderStatus(updateOrder.getOrderStatus());
				orderEle.setListOfProducts(updateOrder.getListOfProducts());
				break;
			}
			
		}
		
		order_repository.save(updateOrder);
		
		return updateOrder;
	}

	@Override
	public Order removeOrder(Order order) {
		
		Order fetchedOrderFromDb = order_repository.findById(order.getOrderId()).orElseThrow();
		//throw error if not found in db
		
		order_repository.delete(fetchedOrderFromDb);
		
		return fetchedOrderFromDb;
	}

	@Override
	public Order viewOrder(Order order) {
		// TODO Auto-generated method stub
		
		Order fetchedOrder = order_repository.findById(order.getOrderId()).orElseThrow();
		//throw error no  such order in cart;
		
		return order;
	}

	@Override
	public List<Order> viewAllOrders(LocalDate date) {
		
		List<Order> listOfOrder = order_repository.findAll();
		List<Order> OrderListByDate = new ArrayList<>();
		for(Order OrderEle : listOfOrder) {
			
			if(OrderEle.getOrderDate() == date) {
				
				OrderListByDate.add(OrderEle);
				
			}
			
		}
		
		return OrderListByDate;
	}

	@Override
	public List<Order> viewAllOrdersByLocation(String loc) {
		
		
		
		return null;
	}

	@Override
	public List<Order> viewAllOrdersByUserId(String userid) {
		
		
		
		return null;
	}

}

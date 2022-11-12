package com.Shopping.Services.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Shopping.Exception.CustomerException;
import com.Shopping.Exception.LoginException;
import com.Shopping.Exception.OrderException;
import com.Shopping.Model.CurrentUserSession;
import com.Shopping.Model.Customer;
import com.Shopping.Model.Order;
import com.Shopping.Model.Product;
import com.Shopping.Repository.CartRepo;
import com.Shopping.Repository.CurrentUserSessionRepo;
import com.Shopping.Repository.CustomerRepo;
import com.Shopping.Repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	CustomerRepo customer_repository;
	
	@Autowired
	OrderRepo order_repository;
	
	@Autowired
	CurrentUserSessionRepo loggedInCustomer;
	
	@Autowired
	CartRepo cart_repo;
	
	@Override
	public Order addOrder(Order order, String key)throws CustomerException,LoginException{
		
		//login validation 
		Customer customer_relatedTo_order = loginValidation(key);
		
		
		//to check if the customer exists in the database;
		
		Integer customerId = customer_relatedTo_order.getCustomerId();
		
		//throws no customer for the given id exists.
		Customer fetchedCustomerDb = customer_repository.findById(customerId).orElseThrow(()-> new CustomerException("No such customer with the given id exists in the database"));
		
		//fetched cart from customer takes in the products list and injects it in the order list
		order.setCustomer(fetchedCustomerDb);
		List<Product> product_list = fetchedCustomerDb.getCart().getProductList();
		
		List<Product> newProductList = new ArrayList<>();
		
		for(Product ele : product_list) {
			
			newProductList.add(ele);
			
		}
		
		order.setListOfProducts(newProductList);
		
		cart_repo.delete(fetchedCustomerDb.getCart());
		order_repository.save(order);
		
		fetchedCustomerDb.getOrdersList().add(order);
		
		
		return order;
	}
	
	public Customer loginValidation(String key) throws LoginException,CustomerException{
		
		CurrentUserSession checkCustomer = loggedInCustomer.findByUuid(key);
		
		if(checkCustomer == null) throw new LoginException("Customer not logged in");
		
		Customer loggedCustomer  = customer_repository.findById(checkCustomer.getUserId()).orElseThrow(()-> new CustomerException("No Such Customer in Db"));
		
		return loggedCustomer;
		
	}

	
	
	@Override
	public Order updateOrder(Order updateOrder,String key) throws OrderException,CustomerException,LoginException{
		
		Customer fetchedCustomerFromKey = loginValidation(key);
		
		//check if such order exists in db
		Order fetchedOrderDb = order_repository.findById(updateOrder.getOrderId()).orElseThrow(()-> new OrderException("No such order to update in the DB"));
		
		
		//check with db if that customer exists in database;
		Customer fetchedCustomerDb = customer_repository.findById(fetchedCustomerFromKey.getCustomerId()).orElseThrow(()-> new CustomerException("No such customer with the given id exists in the database"));
		
		List<Order> listOfOrders = fetchedCustomerFromKey.getOrdersList();
		
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
	public Order removeOrder(Order order) throws OrderException, CustomerException{
		
		Order fetchedOrderFromDb = order_repository.findById(order.getOrderId()).orElseThrow(()-> new OrderException("No such order int the database"));
		//throw error if not found in db
		
		//throw error if customer order mismatch
		Customer fetchedCustomer = customer_repository.findById(fetchedOrderFromDb.getCustomer().getCustomerId()).orElseThrow(()-> new CustomerException("Customer in the order mismatch"));
		
		order_repository.delete(fetchedOrderFromDb);
		
		return fetchedOrderFromDb;
	}

	@Override
	public Order viewOrder(Order order) throws OrderException{
		// TODO Auto-generated method stub
		
		Order fetchedOrder = order_repository.findById(order.getOrderId()).orElseThrow(()-> new OrderException("No such order exists in the Database"));
		//throw error no  such order in cart;
		
		
		
		return order;
	}

	@Override
	public List<Order> viewAllOrders(LocalDate date) throws OrderException{
		
		List<Order> listOfOrder = order_repository.findAll();
		
		if(listOfOrder.isEmpty()) throw new OrderException("There are No orders in the database");
		
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
		
		// need some clarification.
		
		return null;
	}

	@Override
	public List<Order> viewAllOrdersByUserId(Integer userid) throws CustomerException{
		
		Customer fetchCustomer = customer_repository.findById(userid).orElseThrow(()-> new CustomerException("no such user in DataBase"));
		
		
		
		return fetchCustomer.getOrdersList();
	}

}

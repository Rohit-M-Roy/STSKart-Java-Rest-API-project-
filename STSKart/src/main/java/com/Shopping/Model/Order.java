package com.Shopping.Model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Order {
	
	private Integer orderId;
	private LocalDate orderDate;
	private String orderStatus;
	private Customer customer;
	private Address address;
	private Map<Product,Integer> productList = new HashMap<>();
}

package com.Shopping.Services;

import org.springframework.stereotype.Service;

import com.Shopping.Exception.CustomerException;
import com.Shopping.Model.Address;
import com.Shopping.Model.Customer;


@Service
public interface CustomerService {
	
	public Customer createAccount(Customer cum) throws CustomerException;
	
	public Address setTheAddress(Address add,Integer id) throws CustomerException;
	
	public String addProduct(Integer i,Integer cusId)throws CustomerException;
	
	
	
}

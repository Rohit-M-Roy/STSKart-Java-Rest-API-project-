package com.Shopping.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Shopping.Exception.CustomerException;
import com.Shopping.Exception.LoginException;
import com.Shopping.Model.Address;
import com.Shopping.Model.Customer;
import com.Shopping.Services.CustomerService;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
	@Autowired
	private CustomerService cs;

	@PostMapping("/create")
	public ResponseEntity<Customer> createAccount(@Valid @RequestBody Customer cus) throws CustomerException {
		Customer adm = cs.createAccount(cus);

		return new ResponseEntity<Customer>(adm, HttpStatus.OK);
	}

	@PutMapping("/addAddress")
	public ResponseEntity<Address> putAddress(@RequestBody Address a, @RequestParam Integer i, @RequestParam String key)
			throws CustomerException, LoginException {

		Address ad = cs.setTheAddress(a, i,key);

		return new ResponseEntity<>(ad, HttpStatus.OK);
	}

	@PutMapping("addToCart")
	public ResponseEntity<String> addProductToCart(@RequestParam Integer productId, @RequestParam Integer customerId,
			@RequestParam String key) throws CustomerException, LoginException {
		String x = cs.addProduct(productId, customerId, key);
		return new ResponseEntity<>(x, HttpStatus.OK);
	}

}

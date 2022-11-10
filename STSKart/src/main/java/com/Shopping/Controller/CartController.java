package com.Shopping.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Shopping.Model.Cart;
import com.Shopping.Model.Product;
import com.Shopping.Services.CartService;


@RestController
@RequestMapping(value = "/cart")
public class CartController {
	
	@Autowired
	private CartService cartservice;
	
	
	@PutMapping("/removeProduct/")
	public ResponseEntity<Cart> removeProductFromCart(@RequestBody Product product, String key, Integer cid){
		
//		cartservice.removeproductFromCart(product, cid , key);
		
		return new ResponseEntity<Cart>(HttpStatus.ACCEPTED);
	}

}

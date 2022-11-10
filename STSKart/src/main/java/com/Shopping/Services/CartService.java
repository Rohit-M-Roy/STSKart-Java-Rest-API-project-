package com.Shopping.Services;

import org.springframework.stereotype.Service;

import com.Shopping.Exception.CartException;
import com.Shopping.Model.Cart;
import com.Shopping.Model.Product;

@Service
public interface CartService {
	
	
	public Cart removeproductFromCart(Product product,String key ,Integer cid) throws Exception;
	
	
	public Cart updateProductQuantity(Integer cid, Product product, Integer quantity, String key) throws Exception;
	
	

}

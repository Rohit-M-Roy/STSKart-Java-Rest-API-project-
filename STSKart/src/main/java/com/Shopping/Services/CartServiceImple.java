package com.Shopping.Services;

import java.util.Optional;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Shopping.Exception.CartException;
import com.Shopping.Exception.CustomerException;
import com.Shopping.Model.Cart;
import com.Shopping.Model.Customer;
import com.Shopping.Model.Product;
import com.Shopping.Repository.CartRepo;
import com.Shopping.Repository.CustomerRepo;

@Service
public class CartServiceImple implements CartService {
	
	@Autowired
	private CartRepo cartrepo;
	
	@Autowired
	private CustomerRepo crepo;
 
	@Override
	public Cart removeproductFromCart(Product product,String key ,Integer cid ) throws Exception {
		
		 Optional<Customer> opt = crepo.findById(cid);
		 
		 if(opt.isPresent()) {
			 Customer cur = opt.get();
			 
			 
		
		 }
		  
		  
		
		throw new CustomerException("Invalid customerId");
	}

	@Override
	public Cart updateProductQuantity(Cart cart, Product product, Integer quantity, String key) throws Exception {
				
		return null;
		
	}

	

}

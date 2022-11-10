package com.Shopping.Services;

import java.util.List;
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
	public Cart removeproductFromCart(Product product,String key ,Integer cid ) throws CustomerException {
		
		 Optional<Customer> opt = crepo.findById(cid);
		 
		 if(opt.isPresent()) {
			 Customer cur = opt.get();
			 
			 Cart cart_cus= cur.getCart();
		
			 List<Product> li= cart_cus.getProductList();
			 
			boolean flag=false;
			 
			 for(int i=0;i<li.size();i++){
				 if(li.get(i).getProductId()==product.getProductId()) {
					 li.remove(product);
					 flag=true;
					 break;
				 }
			 }
			 
			 cart_cus.setProductList(li);
			 cur.setCart(cart_cus);
			 crepo.save(cur);
			 
			 return cart_cus;
		 }
		  
		  
		
		throw new CustomerException("Invalid customerId");
	}

	@Override
	public Cart updateProductQuantity(Integer cid, Product product, Integer quantity, String key) throws CustomerException{
				
		Optional<Customer> opt = crepo.findById(cid);
		
		 if(opt.isPresent()) {
			 Customer cur = opt.get();
			 
			 Cart cart_cus= cur.getCart();
			 List<Product> li= cart_cus.getProductList();
			 
				boolean flag=false;
				 
				 for(int i=0;i<li.size();i++){
					 if(li.get(i).getProductId()==product.getProductId()) {
						 li.get(i).setQuantity(li.get(i).getQuantity()+quantity);
						 flag=true;
						 break;
					 }
				 }
		 
				 cart_cus.setProductList(li);
				 cur.setCart(cart_cus);
				 crepo.save(cur);
		 
				 return cart_cus;
		 }
				 throw new CustomerException("Invalid customerId");
}
}
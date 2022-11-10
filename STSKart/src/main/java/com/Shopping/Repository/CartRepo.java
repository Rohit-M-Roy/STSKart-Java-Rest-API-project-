package com.Shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Shopping.Model.Cart;
import com.Shopping.Model.Product;
import com.Shopping.Model.Seller;
import com.Shopping.Model.SellerProducts;

public interface CartRepo extends JpaRepository<Cart, Integer>  {

	public Product findByProduct(Integer productId);
	
}
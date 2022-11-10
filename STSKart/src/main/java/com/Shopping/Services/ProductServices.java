package com.Shopping.Services;

import java.util.*;

import com.Shopping.Model.Product;

public interface ProductServices {
	
	public List<Product> viewAllProducts();
	public Product addProduct(Product product);
	public Product updateProduct(Product product);
	public Product viewProduct(Integer productId);
	public List<Product> viewProductByCategory(String category);
	public Product removeProduct(Integer productId);

}

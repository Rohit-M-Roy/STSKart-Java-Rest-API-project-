package com.Shopping.Services;

import java.util.*;

import com.Shopping.Exception.ProductException;
import com.Shopping.Model.Product;

public interface ProductServices {
	
	public List<Product> viewAllProducts() throws ProductException;
	public Product addProduct(Product product) throws ProductException;
	public Product updateProduct(Product product) throws ProductException;
	public Product viewProduct(Integer productId) throws ProductException;
	public List<Product> viewProductByCategory(String category) throws ProductException;
	public Product removeProduct(Integer productId) throws ProductException;

}

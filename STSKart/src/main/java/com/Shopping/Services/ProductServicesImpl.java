package com.Shopping.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Shopping.Exception.ProductException;
import com.Shopping.Model.CurrentUserSession;
import com.Shopping.Model.Product;
import com.Shopping.Repository.ProductRepo;

@Service
public class ProductServicesImpl implements ProductServices{

	@Autowired
	private ProductRepo pRepo;
	
	@Override
	public List<Product> viewAllProducts() throws ProductException {
		List<Product> allProducts = pRepo.findAll();
		if(allProducts.size() != 0) {			
			return allProducts;
		}
		else throw new ProductException("No Products found");
	}

	@Override
	public Product addProduct(Product product) {
		Product saveProduct = pRepo.save(product);
		return saveProduct;
	}

	@Override
	public Product updateProduct(Product product) throws ProductException {
		Optional<Product> findProduct = pRepo.findById(product.getProductId());
		if(findProduct.isPresent()) {
			Product updatedProduct = pRepo.save(product);
			return updatedProduct;
		}
		else throw new ProductException("Invalid Product Details");
	}

	@Override
	public Product viewProduct(Integer productId) throws ProductException {
		return pRepo.findById(productId).orElseThrow(()-> new ProductException("No Product found with this ID: "+productId));
	}

	@Override
	public List<Product> viewProductByCategory(String category) throws ProductException {
		List<Product> products = pRepo.findByCategory(category);
		if(products.size() != 0) {
			return products;
		}
		else throw new ProductException("No Products found in this Category: "+category);
	}

	@Override
	public Product removeProduct(Integer productId) throws ProductException {
		Product findProduct = pRepo.findById(productId).orElseThrow(()-> new ProductException("No Product found with this ID: "+productId));
		pRepo.delete(findProduct);
		return findProduct;
	}

}

package com.Shopping.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Shopping.Model.Product;
import com.Shopping.Repository.ProductRepo;

@Service
public class ProductServicesImpl implements ProductServices{

	@Autowired
	ProductRepo pRepo;
	
	@Override
	public List<Product> viewAllProducts() {
		List<Product> allProducts = pRepo.findAll();
		if(allProducts.size() != 0) {			
			return allProducts;
		}
		else return null;
	}

	@Override
	public Product addProduct(Product product) {
		Product saveProduct = pRepo.save(product);
		return saveProduct;
	}

	@Override
	public Product updateProduct(Product product) {
		Optional<Product> findProduct = pRepo.findById(product.getProductId());
		if(findProduct.isPresent()) {
			Product updatedProduct = pRepo.save(product);
			return updatedProduct;
		}
		else {
			return null;
		}
	}

	@Override
	public Product viewProduct(Integer productId) {
		return pRepo.findById(productId).orElseThrow();
	}

	@Override
	public List<Product> viewProductByCategory(String category) {
		List<Product> products = pRepo.findByCategory(category);
		if(products.size() != 0) {
			return products;
		}
		else {
			return null;
		}
	}

	@Override
	public Product removeProduct(Integer productId) {
		Product findProduct = pRepo.findById(productId).orElseThrow();
		pRepo.delete(findProduct);
		return findProduct;
	}

}

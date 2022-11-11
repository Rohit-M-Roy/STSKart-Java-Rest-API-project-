package com.Shopping.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Shopping.Exception.ProductException;
import com.Shopping.Model.Product;
import com.Shopping.Services.ProductServices;

@RestController
public class ProductController {
	
	@Autowired
	ProductServices productServices;
	
	@GetMapping("/product")
    public ResponseEntity<List<Product>> viewProductsList() throws ProductException{
    	List<Product> allProducts = productServices.viewAllProducts();
    	return new ResponseEntity<List<Product>>(allProducts, HttpStatus.OK);
    }
	
    @PostMapping("/product/add/")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product ) throws ProductException{
    	Product addedProduct = productServices.addProduct(product);
    	return new ResponseEntity<Product>(addedProduct, HttpStatus.CREATED);
    }
	
    @PutMapping("/product/update/")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws ProductException{
    	Product updatedProduct = productServices.updateProduct(product);
    	return new ResponseEntity<Product>(updatedProduct, HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/product/id/{productId}")
    public ResponseEntity<Product> viewProductByID(@PathVariable("productId") Integer productId) throws ProductException{
    	Product existingProduct = productServices.viewProduct(productId);
    	return new ResponseEntity<Product>(existingProduct, HttpStatus.OK);
    }
    
    @GetMapping("/product/category/{category}")
    public ResponseEntity<List<Product>> viewProductByCategory(@PathVariable("category") String category) throws ProductException{
    	List<Product> existingProduct = productServices.viewProductByCategory(category);
    	return new ResponseEntity<List<Product>>(existingProduct, HttpStatus.OK);
    }
    
    @DeleteMapping("/product/delete/{productId}")
    public ResponseEntity<Product> deleteProductByID(@PathVariable("productId") Integer productId) throws ProductException{
    	Product deletedProduct = productServices.removeProduct(productId);
    	return new ResponseEntity<Product>(deletedProduct, HttpStatus.OK);
    }
}

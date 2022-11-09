package com.shopping.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	private String productName;
	
	private double price;
	
	private Integer quantity;
	
	private String colour;

	private String dimension;
	private String specification;
	private String manufacutrer;

	
//	@ManyToOne(cascade = CascadeType.ALL)
//	private Seller seller;
//	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "")
	private Category category;
	
}

//package com.shopping.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.OneToMany;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Entity
//public class Seller extends User{
//
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Integer sellerId;
//	
//	@JsonIgnore
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "seller")
//	private List<Product> sellerProducts=new ArrayList<>();
//	
//	
//	
//}

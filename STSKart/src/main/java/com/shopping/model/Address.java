package com.shopping.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	
	private String streetNo;
	
	private String buildingName;
	
	private String city;
	
	private String state;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	private String country;
	
	private String pincode;
	
	
	
}

package com.shopping.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer extends User{
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer CustomerId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;
}
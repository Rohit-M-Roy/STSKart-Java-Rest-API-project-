package com.shopping.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.catalina.util.CustomObjectInputStream;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;

	private LocalDate orderDate;
	
	private String orderStatus;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	@Embedded
	private Address address;
	
	

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
	private List<Product> productsOrdered = new ArrayList<>();
}

package com.Shopping.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer extends User{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "customer")
	private Address address;
	
	@JsonIgnore
<<<<<<< HEAD

=======
>>>>>>> 92dfbfca3b837f0fbbb2c5a02d943572d89b340e
	@OneToMany(mappedBy = "customer",orphanRemoval = true)
	private List<Order> ordersList = new ArrayList<>() ;

	@OneToOne(cascade = CascadeType.ALL,mappedBy = "customer")
	private Cart cart;
<<<<<<< HEAD
	
=======
>>>>>>> 92dfbfca3b837f0fbbb2c5a02d943572d89b340e

	
}


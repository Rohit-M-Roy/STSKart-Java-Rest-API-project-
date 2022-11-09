package com.shopping.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy.Eager;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer extends User{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer CustomerId;
	
	@Embedded
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "customer")
	private Cart cart;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	private List<Order> ordersList = new ArrayList<>() ;
	
	
}


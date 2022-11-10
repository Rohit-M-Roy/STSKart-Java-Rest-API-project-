package com.Shopping.Model;

import lombok.Data;


@Data
public class Address {
	
	
	private Integer addressId;
	private String streetNo;
	private String buildingName;
	private String city;
	private String state;
	private String country;
	private String pincode;

}

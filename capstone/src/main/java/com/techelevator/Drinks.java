package com.techelevator;

import java.math.BigDecimal;

public class Drinks extends Product {

	private String type;

	public Drinks(String location, String name, BigDecimal price, int stock) {
		type = "Drinks";
		super.setLocation(location);
		super.setName(name);
		super.setPrice(price);
		super.setStock(stock);
		super.setSound("Glug Glug, Yum!");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}

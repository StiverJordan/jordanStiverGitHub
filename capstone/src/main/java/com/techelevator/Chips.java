package com.techelevator;

import java.math.BigDecimal;

public class Chips extends Product {
	
	private String type;

	public Chips(String location, String name, BigDecimal price, int stock) {
		type = "Chips";
		super.setLocation(location);
		super.setName(name);
		super.setPrice(price);
		super.setStock(stock);
		super.setSound("Crunch Crunch, Yum!");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}





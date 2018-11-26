package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Product {

	private String type;

	public Gum(String location, String name, BigDecimal price, int stock) {
		type = "Gum";
		super.setLocation(location);
		super.setName(name);
		super.setPrice(price);
		super.setStock(stock);
		super.setSound("Chew Chew, Yum!");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}


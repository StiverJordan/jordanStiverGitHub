package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Product {

	private String type;

	public Candy(String location, String name, BigDecimal price, int stock) {
		type = "Candy";
		super.setLocation(location);
		super.setName(name);
		super.setPrice(price);
		super.setStock(stock);
		super.setSound("Munch Munch, Yum!");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}




package com.techelevator;

import java.math.BigDecimal;

public class Bills extends Money {

	private BigDecimal type;
	
	public Bills(BigDecimal amount) {
		type = new BigDecimal("Bills");
		super.setAmount(amount);
		
	}

	public BigDecimal getType() {
		return type;
	}

//	public void setType(String type) {
//		this.type = type;
//	}
}

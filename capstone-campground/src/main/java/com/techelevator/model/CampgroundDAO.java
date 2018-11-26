package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;

public interface CampgroundDAO {
	
	public List<Campground> getCampgroundsById(long id);
	
	public BigDecimal getDailyFeeById(long id);

}

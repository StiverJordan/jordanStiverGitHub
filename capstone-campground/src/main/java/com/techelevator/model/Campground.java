package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Campground {
	
	long campgroundId;
	long parkId;
	String name;
	String openFromMm;
	String openToMm;
	BigDecimal dailyFee;
	
	public long getCampgroundId() {
		return campgroundId;
	}
	public void setCampgroundId(long campgroundId) {
		this.campgroundId = campgroundId;
	}
	public long getParkId() {
		return parkId;
	}
	public void setParkId(long parkId) {
		this.parkId = parkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpenFromMm() {
		return openFromMm;
	}
	public void setOpenFromMm(String openFromMm) {
		this.openFromMm = openFromMm;
	}
	public String getOpenToMm() {
		return openToMm;
	}
	public void setOpenToMm(String openToMm) {
		this.openToMm = openToMm;
	}
	public BigDecimal getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}
}

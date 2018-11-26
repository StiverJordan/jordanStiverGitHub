package com.techelevator.model;

import java.math.BigDecimal;

public class NationalPark {
	
	private String code;
	private String name;
	private String state;
	private Long acreage;
	private Long elevationFeet;
	private double milesOfTrail;
	private int numberOfCampsites;
	private String climate;
	private int yearFounded;
	private Long annualVisitors;
	private String inspQuote;
	private String inspQuoteSrc;
	private String parkDescription;
	private BigDecimal entryFee;
	private int numberOfAnimalSpecies;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getAcreage() {
		return acreage;
	}
	public void setAcreage(Long acreage) {
		this.acreage = acreage;
	}
	public Long getElevationFeet() {
		return elevationFeet;
	}
	public void setElevationFeet(Long elevationFeet) {
		this.elevationFeet = elevationFeet;
	}
	public double getMilesOfTrail() {
		return milesOfTrail;
	}
	public void setMilesOfTrail(double milesOfTrail) {
		this.milesOfTrail = milesOfTrail;
	}
	public int getNumberOfCampsites() {
		return numberOfCampsites;
	}
	public void setNumberOfCampsites(int numberOfCampsites) {
		this.numberOfCampsites = numberOfCampsites;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public int getYearFounded() {
		return yearFounded;
	}
	public void setYearFounded(int yearFounded) {
		this.yearFounded = yearFounded;
	}
	public Long getAnnualVisitors() {
		return annualVisitors;
	}
	public void setAnnualVisitors(Long annualVisitors) {
		this.annualVisitors = annualVisitors;
	}
	public String getInspQuote() {
		return inspQuote;
	}
	public void setInspQuote(String inspQuote) {
		this.inspQuote = inspQuote;
	}
	public String getInspQuoteSrc() {
		return inspQuoteSrc;
	}
	public void setInspQuoteSrc(String inspQuoteSrc) {
		this.inspQuoteSrc = inspQuoteSrc;
	}
	public String getParkDescription() {
		return parkDescription;
	}
	public void setParkDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}
	public BigDecimal getEntryFee() {
		return entryFee;
	}
	public void setEntryFee(BigDecimal entryFee) {
		this.entryFee = entryFee;
	}
	public int getNumberOfAnimalSpecies() {
		return numberOfAnimalSpecies;
	}
	public void setNumberOfAnimalSpecies(int numberOfAnimalSpecies) {
		this.numberOfAnimalSpecies = numberOfAnimalSpecies;
	}
	

}

package com.techelevator.model;

public class Weather {

	private String code;
	private int fivedayforecast;
	private int low;
	private int high;
	private String forecast;
	private String advisory;
	
	public int toCelcius(int temp) {
		double douTemp = (double)temp;
		
		douTemp = ((douTemp - 32) * (5.0/9.0));
		int intTemp = (int)douTemp;
		
		
		return intTemp;
		
	}

	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getFivedayforecast() {
		return fivedayforecast;
	}
	public void setFivedayforecast(int fivedayforecast) {
		this.fivedayforecast = fivedayforecast;
	}
	public int getLow() {
		return low;
	}
	public void setLow(int low) {
		this.low = low;
	}
	public int getHigh() {
		return high;
	}
	public void setHigh(int high) {
		this.high = high;
	}
	public String getForecast() {
		if (forecast.equals("partly cloudy")) {
			forecast = "partlyCloudy";
			return forecast;
		}
		else {
			return forecast;
		}
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	public String getAdvisory() {
		return advisory;
	}
	public void setAdvisory(String advisory) {
		this.advisory = advisory;
	}
	
}

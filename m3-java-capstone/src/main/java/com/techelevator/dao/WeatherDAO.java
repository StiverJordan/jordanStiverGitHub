package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Weather;

public interface WeatherDAO {

	List<Weather> getAllWeather(String code);

	public List<Weather> changeTemp(List<Weather> weathers);

	public void makeAdvisory(Weather weather);

}
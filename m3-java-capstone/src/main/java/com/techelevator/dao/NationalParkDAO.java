package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.NationalPark;

public interface NationalParkDAO {

	List<NationalPark> getAllParks();

	NationalPark getParkInfo(String code);

	NationalPark getMostSurveyed();

	public List<NationalPark> getAllSurveyed();

}

package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.NationalPark;

@Component
public class JdbcNationalParkDAO implements NationalParkDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcNationalParkDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	public List<NationalPark> getAllParks() {
		List<NationalPark> allParks = new ArrayList<NationalPark>();
		String sqlForParkList = "SELECT * from park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlForParkList);
		while (results.next()) {
			allParks.add(mapRowSet(results));
		}
		return allParks;
	}

	public NationalPark getMostSurveyed() {
		String sqlForMostSurveyedCode = "SELECT p.parkcode, COUNT(sr.parkcode) FROM survey_result sr "
				+ "JOIN park p ON sr.parkcode = p.parkcode GROUP BY p.parkcode LIMIT 1";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlForMostSurveyedCode);
		NationalPark park = new NationalPark();
		while (results.next()) {
			String code = results.getString("parkcode");
			park = getParkInfo(code);
		}
		return park;
	}

	public List<NationalPark> getAllSurveyed() {
		String sqlForMostSurveyedCode = "SELECT p.parkcode, COUNT(sr.parkcode) FROM survey_result sr "
				+ "JOIN park p ON sr.parkcode = p.parkcode GROUP BY p.parkcode";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlForMostSurveyedCode);
		NationalPark park = new NationalPark();
		List<NationalPark> topParks = new ArrayList<NationalPark>();
		while (results.next()) {
			String code = results.getString("parkcode");
			park = getParkInfo(code);
			topParks.add(park);
		}
		return topParks;
	}

	@Override
	public NationalPark getParkInfo(String code) {
		String sqlForParkList = "SELECT * from park WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlForParkList, code);
		NationalPark park = new NationalPark();
		while (results.next()) {
			park = mapRowSet(results);
		}
		return park;
	}

	private NationalPark mapRowSet(SqlRowSet results) {
		NationalPark park = new NationalPark();
		park.setCode(results.getString("parkcode"));
		park.setName(results.getString("parkname"));
		park.setState(results.getString("state"));
		park.setAcreage(results.getLong("acreage"));
		park.setElevationFeet(results.getLong("elevationinfeet"));
		park.setMilesOfTrail(results.getDouble("milesoftrail"));
		park.setNumberOfCampsites(results.getInt("numberofcampsites"));
		park.setClimate(results.getString("climate"));
		park.setYearFounded(results.getInt("yearfounded"));
		park.setAnnualVisitors(results.getLong("annualvisitorcount"));
		park.setInspQuote(results.getString("inspirationalquote"));
		park.setInspQuoteSrc(results.getString("inspirationalquotesource"));
		park.setParkDescription(results.getString("parkdescription"));
		park.setEntryFee(results.getBigDecimal("entryfee"));
		park.setNumberOfAnimalSpecies(results.getInt("numberofanimalspecies"));
		return park;
	}
}

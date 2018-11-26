package com.techelevator.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Campground;
import com.techelevator.model.CampgroundDAO;

public class JDBCCampgroundDAO implements CampgroundDAO {
	
private JdbcTemplate jdbcTemplate;
	
	public JDBCCampgroundDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Campground> getCampgroundsById(long id) {
		List<Campground> allCampgroundList = new ArrayList<Campground>();
		String sqlGetAllCampgrounds = "SELECT * from campground where park_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllCampgrounds, id);
		while (results.next()) {
			allCampgroundList.add(mapRowToCampground(results));
		}
		return allCampgroundList;
	}

	@Override
	public BigDecimal getDailyFeeById(long id) {
		String sqlGetCostById = "SELECT daily_fee FROM campground WHERE campground_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetCostById, id);
		result.next();
		BigDecimal cost = result.getBigDecimal("daily_fee");
		return cost;
	}
	
	private Campground mapRowToCampground(SqlRowSet results) {
		Campground c = new Campground();
		c.setCampgroundId(results.getLong("campground_id"));
		c.setParkId(results.getLong("park_id"));
		c.setName(results.getString("name"));
		c.setOpenFromMm(results.getString("open_from_mm"));
		c.setOpenToMm(results.getString("open_to_mm"));
		c.setDailyFee(results.getBigDecimal("daily_fee"));
		return c;
	}

}

package com.techelevator.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Park;
import com.techelevator.model.ParkDAO;
import com.techelevator.model.Site;

public class JDBCParkDAO implements ParkDAO {
	
private JdbcTemplate jdbcTemplate;
	
	public JDBCParkDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> allParkList = new ArrayList<Park>();
		String sqlGetAllParks = "SELECT * from park ORDER BY name";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
		while (results.next()) {
			allParkList.add(mapRowToPark(results));
		}
		return allParkList;
	}
	
	private Park mapRowToPark(SqlRowSet results) {
		Park p = new Park();
		p.setParkId(results.getLong("park_id"));
		p.setName(results.getString("name"));
		p.setLocation(results.getString("location"));
		p.setEstablishDate(results.getDate("establish_date").toLocalDate());
		p.setArea(results.getLong("area"));
		p.setVisitors(results.getLong("visitors"));
		p.setDescription(results.getString("description"));
		return p;
	}
	
		

}

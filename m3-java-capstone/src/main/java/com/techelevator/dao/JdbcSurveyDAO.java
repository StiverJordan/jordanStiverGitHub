package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Survey;

@Component
public class JdbcSurveyDAO implements SurveyDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcSurveyDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	private Long getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_surveyid')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long id = null;
		if (results.next()) {
			id = results.getLong(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next survey id from sequence");
		}
		return id;
	}

	public void saveSurvey(Survey survey) {
		Long id = getNextId();
		survey.setId(id);
		String sqlSaveSurvey = "INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlSaveSurvey, id, survey.getCode(), survey.getEmail(), survey.getState(),
				survey.getActivityLevel());
	}

}

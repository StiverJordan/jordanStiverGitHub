package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.Weather;

@Component
public class JdbcWeatherDAO implements WeatherDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcWeatherDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getAllWeather(String code) {
		List<Weather> allWeather = new ArrayList<>();
		String sqlRowSetWeather = "SELECT * from weather WHERE parkcode = ?";
		SqlRowSet row = jdbcTemplate.queryForRowSet(sqlRowSetWeather, code);
		while (row.next()) {
			allWeather.add(mapRowToWeather(row));
		}
		return allWeather;
	}

	private Weather mapRowToWeather(SqlRowSet row) {
		Weather weather = new Weather();
		weather.setCode(row.getString("parkcode"));
		weather.setFivedayforecast(row.getInt("fivedayforecastvalue"));
		weather.setLow(row.getInt("low"));
		weather.setHigh(row.getInt("high"));
		weather.setForecast(row.getString("forecast"));
		return weather;
	}

	public List<Weather> changeTemp(List<Weather> weathers) {
		List<Weather> newWeather = new ArrayList<Weather>();
		for (Weather w : weathers) {
			int high = w.toCelcius(w.getHigh());
			w.setHigh(high);
			int low = w.toCelcius(w.getLow());
			w.setLow(low);
			newWeather.add(w);
		}
		return newWeather;

	}

	public void makeAdvisory(Weather weather) {
		String forecast = weather.getForecast();
		int high = weather.getHigh();
		int low = weather.getLow();
		String advisory = "";
		switch (forecast) {
		case "snow":
			advisory = "Make sure to pack shoes!";
			break;
		case "rain":
			advisory = "Make sure to pack rain gear and waterproof shoes!";
			break;
		case "thunderstorms":
			advisory = "Seek shelter and avoid hiking on exposed trails!";
			break;
		case "sunny":
			advisory = "Make sure to pack sunblock!";
			break;
		case "partlyCloudy":
			advisory = "";
			break;
		case "cloudy":
			advisory = "";
			break;
		}

		if (high > 75) {
			advisory += "Bring an extra gallon of water!";
		}
		if (high - low >= 20) {
			advisory += "Wear breathable clothing!";
		}
		if (low < 20) {
			advisory += "Over exposure to cold temperatures can lead to frost bite or hypothermia!";
		}

		if (advisory.equals("")) {
			advisory = "Just Have Fun!";
		}

		weather.setAdvisory(advisory);
	}

}

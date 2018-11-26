package com.techelevator.npgeek;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.dao.NationalParkDAO;
import com.techelevator.dao.SurveyDAO;
import com.techelevator.dao.WeatherDAO;
import com.techelevator.model.NationalPark;
import com.techelevator.model.Survey;
import com.techelevator.model.Weather;

@Controller
@SessionAttributes({"park", "weathers" })

public class NpController {
	
	
	
	@Autowired
	private NationalParkDAO nationalParkDao;
	@Autowired
	private WeatherDAO weatherDao;
	@Autowired
	private SurveyDAO surveyDao;

	@RequestMapping(path= {"/", "/homePage"} , method=RequestMethod.GET)
	public String displayHomePage(ModelMap map) {
		
		List<NationalPark> parks = nationalParkDao.getAllParks();
		map.put("parks", parks);
		
		
		return "homePage";
	}
	

	
	
	@RequestMapping(path= "/parksDetails" , method=RequestMethod.GET)
	public String displayParksDetailsList(@RequestParam String codeUrl, ModelMap map) {
		
		NationalPark park = nationalParkDao.getParkInfo(codeUrl);
		map.put("park", park); 
		
		return "parksDetails";
	}
	
	@RequestMapping(path= "/parkWeather" , method=RequestMethod.GET)
	public String displayParkWeather(@RequestParam String degree, ModelMap map) {
		
		NationalPark park = (NationalPark)map.get("park");
		
		List<Weather> weathers = weatherDao.getAllWeather(park.getCode());
		for(Weather w : weathers) {
			weatherDao.makeAdvisory(w);
		}
		if (degree.equals("c")) {
			List <Weather> newWeather = weatherDao.changeTemp(weathers);
			map.put("weathers", newWeather);
		}
		
		else {
			map.put("weathers", weathers);
		}
		
		return "parkWeather";
	}
	
	
	
	@RequestMapping(path= "/survey" , method=RequestMethod.GET)
	public String displaysurvey() {
		return "survey";
	}
	
	@RequestMapping(path= "/survey", method=RequestMethod.POST)
	public String processSurvey(@RequestParam String code, @RequestParam String email,
								@RequestParam String state, @RequestParam String activityLevel,
								ModelMap map) {
		
		Survey survey = new Survey();
		survey.setCode(code);
		survey.setEmail(email);
		survey.setState(state);
		survey.setActivityLevel(activityLevel);
		surveyDao.saveSurvey(survey);
		
		return "redirect:/completedSurvey";
	}
	
	@RequestMapping("/completedSurvey")
	public String displayCompletedSurvey (ModelMap map) {
		NationalPark topPark = nationalParkDao.getMostSurveyed();
		map.put("topPark", topPark);
		return "completedSurvey";
	}
	
	@RequestMapping("/favoriteParks")
	public String displayFavoriteParks (ModelMap map) {
		List<NationalPark> topParks = nationalParkDao.getAllSurveyed();
		map.put("topParks", topParks);
		return "favoriteParks";
	}
}

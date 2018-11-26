package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.jdbc.JDBCCampgroundDAO;
import com.techelevator.jdbc.JDBCParkDAO;
import com.techelevator.jdbc.JDBCReservationDAO;
import com.techelevator.jdbc.JDBCSiteDAO;
import com.techelevator.model.Campground;
import com.techelevator.model.CampgroundDAO;
import com.techelevator.model.Park;
import com.techelevator.model.ParkDAO;
import com.techelevator.model.Reservation;
import com.techelevator.model.ReservationDAO;
import com.techelevator.model.Site;
import com.techelevator.model.SiteDAO;
import com.techelevator.view.Menu;

public class CampgroundCLI {
	
	private static final String CAMPGROUND_MENU_VIEW_CAMPGROUND = "View Campgrounds";
	private static final String CAMPGROUND_MENU_SEARCH_RESERVATION = "Search for Existing Reservation";
	private static final String CAMPGROUND_MENU_RETURN = "Return to Previous Screen";
	private static final String[] CAMPGROUND_MENU_OPTIONS= new String[] { CAMPGROUND_MENU_VIEW_CAMPGROUND,
																		  CAMPGROUND_MENU_SEARCH_RESERVATION,
																		  CAMPGROUND_MENU_RETURN };
	
	private static final String RESERVATION_MENU_SEARCH = "Search for Available Reservation and Make a Reservation";
	private static final String RESERVATION_MENU_RETURN = "Return to Previous Screen";
	private static final String[] RESERVATION_MENU_OPTIONS = new String[] { RESERVATION_MENU_SEARCH,
																			RESERVATION_MENU_RETURN };
	
	Menu menu;
	ParkDAO parkDAO;
	CampgroundDAO campgroundDAO;
	SiteDAO siteDAO;
	ReservationDAO reservationDAO;
	Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource datasource) {
		this.menu = new Menu(System.in, System.out);
		
		parkDAO = new JDBCParkDAO(datasource);
		campgroundDAO = new JDBCCampgroundDAO(datasource);
		siteDAO = new JDBCSiteDAO(datasource);
		reservationDAO = new JDBCReservationDAO(datasource);
	}
	
	public void run() {
		List<Park> allParks = parkDAO.getAllParks();
		
		Map<String,Integer> menuOptionsToParkMap = new HashMap<String, Integer>();
		String[] parkMenuOptions = new String[allParks.size() + 1];
		
		for (int i = 0; i < allParks.size(); i++) {
			parkMenuOptions[i] = allParks.get(i).getName();
			menuOptionsToParkMap.put(allParks.get(i).getName(), (int)allParks.get(i).getParkId());
		}
		parkMenuOptions[allParks.size()] = "Quit";
		menuOptionsToParkMap.put("Quit", allParks.size() + 1);
		
		while (true) {
			String choice = (String)menu.getChoiceFromOptions(parkMenuOptions);
			if (menuOptionsToParkMap.get(choice) == allParks.size() + 1) {
				input.close();
				System.exit(0);	// Exit normally
			}
			else {
				displayParkInfo(allParks.get(menuOptionsToParkMap.get(choice) - 1));
				handleCampgrounds(allParks.get(menuOptionsToParkMap.get(choice) - 1).getParkId());
			}
		}
	}
	
	private void displayParkInfo(Park p) {
		System.out.println(p.getName() + " National Park");
		System.out.format("%-16s %s\n", "Location:", p.getLocation());
		System.out.format("%-16s %s\n", "Established:", p.getEstablishDate());
		System.out.format("%-16s %,d\n", "Area:", p.getArea());
		System.out.format("%-16s %,d\n", "Annual Visitors:", p.getVisitors());
		System.out.println();
		System.out.println(p.getDescription());
	}
	
	private void handleCampgrounds(long parkId) {
		while (true) {
			String choice = (String)menu.getChoiceFromOptions(CAMPGROUND_MENU_OPTIONS);
			if (choice.equals(CAMPGROUND_MENU_VIEW_CAMPGROUND)) {
				displayCampground(campgroundDAO.getCampgroundsById(parkId));
				reservationHandler(campgroundDAO.getCampgroundsById(parkId));
			}
			else if (choice.equals(CAMPGROUND_MENU_SEARCH_RESERVATION)) {
				searchForExistingReservation(parkId);
			}
			else if (choice.equals(CAMPGROUND_MENU_RETURN)) {
				break;
			}
		}
	}
	private void displayCampground(List<Campground> campground) {
		System.out.format("%-3s %-31s %-9s   %-9s   %9s\n","", "Name" , "Open" , "Close" , "Daily Fee");
		int count = 1;
		for (Campground c : campground) {
			System.out.format("#%-2s %-31s %-9s   %-9s   $%-9s\n",  count ,c.getName() , getMonth(c.getOpenFromMm()) , getMonth(c.getOpenToMm()) , c.getDailyFee().setScale(2, RoundingMode.CEILING));
			count++;
		}
	}
	
	private String getMonth(String month) {
		String result = "";
		switch (month) {
		case "01" : 
			result = "January";
			break;
		case "02" : 
			result = "February";
			break;
		case "03" : 
			result = "March";
			break;
		case "04" : 
			result = "April";
			break;
		case "05" : 
			result = "May";
			break;	
		case "06" : 
			result = "June";
			break;
		case "07" : 
			result = "July";
			break;	
		case "08" : 
			result = "August";
			break;
		case "09" : 
			result = "September";
			break;
		case "10" : 
			result = "October";
			break;
		case "11" : 
			result = "November";
			break;
		case "12" : 
			result = "December";
			break;	
		}
		return result;
	}
	
	private void reservationHandler(List<Campground> campgroundList) {
		while (true) {
			String choice = (String)menu.getChoiceFromOptions(RESERVATION_MENU_OPTIONS);
			if (choice.equals(RESERVATION_MENU_SEARCH)) {
				getReservationInformation(campgroundList);
			}
			else if (choice.equals(RESERVATION_MENU_RETURN)) {
				break;
			}
		}
	}
	
	private void searchForExistingReservation(long parkId) {
		System.out.println();
		System.out.println("Please enter the confirmation number of your reservation");
		long id = input.nextLong();
		input.nextLine();
		if (reservationDAO.searchReservationsById(id).getReservationId() == 0) {
			System.out.println();
			System.out.println("That reservation doesn't exist in our system. Please try again.");
		}
		else {
			Reservation r = reservationDAO.searchReservationsById(id);
			System.out.println();
			System.out.format("%-20s %d\n", "Confirmation Number:", r.getReservationId());
			System.out.format("%-20s %d\n", "Site ID:", r.getSiteId());
			System.out.format("%-20s %s\n", "Name:", r.getName());
			System.out.format("%-20s %s\n", "From Date:", r.getFromDate());
			System.out.format("%-20s %s\n", "To Date:", r.getToDate());
			System.out.format("%-20s %s\n", "Created Date:", r.getCreateDate());	
		}
	}

	private void getReservationInformation(List<Campground> camp) {
		System.out.println("Which Campground (enter 0 to cancel)?");
		long userChoice;
		long campgroundID;
		
		try {
			userChoice = input.nextLong();
			input.nextLine();
			campgroundID = camp.get((int)userChoice - 1).getCampgroundId();
		} catch (InputMismatchException e) {
			System.out.println("Please enter a valid campground. Returning to menu.");
			userChoice = 0;
			campgroundID = 0;
			input.nextLine();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Please enter a valid campground. Returning to menu.");
			userChoice = 0;
			campgroundID = 0;
		}
		
		if (userChoice == 0) {
			return;
		}

		LocalDate[] resDates = getResDates();
		if (resDates == null) {
			System.out.println("Please enter a valid date. Returning to menu.");
			return;
		}
		if (ChronoUnit.DAYS.between(resDates[0], resDates[1]) < 1) {
			System.out.println("Please ensure that the departure date is past the arrival date. Returning to menu.");
			return;
		}
		
		System.out.println("Number of occupants?");
		long numOccupants;
		try {
			numOccupants = input.nextLong();
			input.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Please enter a valid number. Returning to menu.");
			input.nextLine();
			numOccupants = 0;
			return;

		}
		if (numOccupants < 1) {
			System.out.println("Please enter a positive number of occupants. Returning to menu.");
			return;
		}
		
		System.out.println("Do you require accessibility? (y/n)");
		String accessibility = input.nextLine().substring(0, 1).toLowerCase();
		if (!accessibility.equals("y") && !accessibility.equals("n")) {
			System.out.println("Please enter 'y' or 'n'. Returning to menu.");
			return;
		}
		boolean needsAccessability = accessibility.equals("y") ? true :false;
		
		System.out.println("Do you require utilities? (y/n)");
		String utilities = input.nextLine().substring(0, 1).toLowerCase();
		if (!utilities.equals("y") && !utilities.equals("n")) {
			System.out.println("Please enter 'y' or 'n'. Returning to menu.");
			return;
		}
		boolean needsUtilities = utilities.equals("y") ? true :false;		
		
		System.out.println("RV length? (0 if none)");
		long rvLength;
		try {
			rvLength = input.nextLong();
			input.nextLine();	
		} catch (InputMismatchException e) {
			System.out.println("Please enter a valid number. Returning to menu.");
			return;
		}

		
		List<Site> availableSites = siteDAO.searchAvailableReservation(campgroundID,
																		resDates[0],
																		resDates[1],
																		numOccupants,
																		needsAccessability,
																		needsUtilities,
																		rvLength);
		if (availableSites.isEmpty()) {
			System.out.println("No results. Please try again.");
			return;
		}
		
		BigDecimal dailyFee = campgroundDAO.getDailyFeeById(campgroundID);
		long numDays = ChronoUnit.DAYS.between(resDates[0], resDates[1]);
		BigDecimal totalFee = dailyFee.multiply(new BigDecimal("" + numDays));
		totalFee = totalFee.setScale(2, RoundingMode.CEILING);
		
		displayAvailableSites(availableSites, totalFee);
		
		System.out.println("Which site should be reserved? (enter 0 to cancel)");
		long siteToReserve = input.nextLong();
		input.nextLine();
		if (siteToReserve == 0) {
			return;
		}
		
		System.out.println("What name should the reservation be made under?");
		String nameForReservation = input.nextLine();
		
		Reservation r = new Reservation();
		r.setFromDate(resDates[0]);
		r.setToDate(resDates[1]);
		r.setName(nameForReservation);
		r.setSiteId(siteToReserve);
		
		reservationDAO.saveReservation(r);
		
		System.out.println("The reservation has been made and your confirmation number is :" +
				reservationDAO.searchReservationsByName(nameForReservation).getReservationId());
	}
	
	private LocalDate[] getResDates() {
		LocalDate[] resDates = new LocalDate[2];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		System.out.println("What is the arrival date? mm/dd/yyyy");
		String arrivalDate = input.nextLine();
		try {
			resDates[0] = LocalDate.parse(arrivalDate, formatter);
			LocalDate today = null;
			if(resDates[0].compareTo(today.now()) < 0) {
				return null;
			}
		}
		catch (DateTimeParseException e) {
			return null;
		}

		System.out.println("What is the departure date? mm/dd/yyyy");
		String departureDate = input.nextLine();
		try {
			resDates[1] = LocalDate.parse(departureDate, formatter);
		}
		catch (DateTimeParseException e) {
			return null;
		}
		
		return resDates;
	}
	
	private void displayAvailableSites(List<Site> availableSites, BigDecimal totalFee) {
		System.out.println("Results Matching Your Search Criteria:");
		System.out.format("%-14s  %-10s  %-12s  %-6s %-7s %-6s\n", "Campground No.", "Max Occup.", "Accessible?", "RV Len", "Utility", "Cost");
		for (Site s : availableSites) {
			System.out.format("%-14d  %-10d  %-12s  %-6s %-7s %-6s\n", s.getSiteId(),
																	s.getMaxOccupancy(),
																	s.isAccessible() ? "Yes" : "No",
																	s.getMaxRvLength() == 0 ? "N/A" : s.getMaxRvLength(),
																	s.isUtilites() ? "Yes" : "N/A",
																	totalFee);
		}
	}
}

package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import com.techelevator.view.Menu;

public class Inventory {
	private Map<String, Product> inventoryList = new TreeMap<>();
	public Inventory() {
		fillTheList();				 // loads the file into the inventory map
	}
	public void fillTheList() {
		File vendingMachineItem = new File("vendingmachine.csv");

		Scanner fileScanner;
		try {
			fileScanner = new Scanner(vendingMachineItem);
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] item = line.split("\\|");

				if (item[0].contains("A")) {
					inventoryList.put(item[0], new Chips(item[0], item[1], new BigDecimal(item[2]), 5));
				} else if (item[0].contains("B")) {
					inventoryList.put(item[0], new Candy(item[0], item[1], new BigDecimal(item[2]), 5));
				} else if (item[0].contains("C")) {
					inventoryList.put(item[0], new Drinks(item[0], item[1], new BigDecimal(item[2]), 5));
				} else if (item[0].contains("D")) {
					inventoryList.put(item[0], new Gum(item[0], item[1], new BigDecimal(item[2]), 5));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Product> getInventoryList() {
		return inventoryList;
	}

	public void getInventoryListAsString() {
		for (Product entry : inventoryList.values()) {
			String productValues = entry.toString();
			System.out.println(productValues);
		}
	}

	public String getInventoryKey(String key) {
		
		Product result = inventoryList.get(key);
		return result.getName() + " " + "$" + result.getPrice();
	}

	public BigDecimal getSelectionPrice(String key) {

		Product result = inventoryList.get(key);
		return result.getPrice();
	}

	public int getStock( String key) {
		Product result = inventoryList.get(key);
		return result.getStock();
	}
	public void removeFromStock(String key) {
		Product result = inventoryList.get(key);
		if(result.getStock() > 0) {
			result.setStock(result.getStock()- 1);
		}
		
	}
	public String makeSound(String key) {
		Product result = inventoryList.get(key);
		return result.getSound();	
	}

}

package com.techelevator;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT };

	private Menu menu;
	private VendingMachine myMachine = new VendingMachine();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		boolean shouldLoop = true;
		while (shouldLoop) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			// StringArray<Product> salesReport = new StringArray<>();

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayItems(); // should be the method to display a fancy view of all the stuff

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				String itemPicked = chooseItems();// do purchase - good place for a method call
				BigDecimal tendered = enterYourMoney();
				System.out.println("getting ready to dispence product.........\n\n");
				dispenseItem(itemPicked);
				giveChange(itemPicked, tendered);
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				// do any end of program processing - good place for a method call
				shouldLoop = false;
			}
		}

	}

	public void displayItems() { // prints the items to the console
		Inventory myInventory = myMachine.getMyInventory();
		myInventory.getInventoryListAsString();
	}

	@SuppressWarnings("resource")
	public String userChoice() {
		Scanner userInput = new Scanner(System.in);
		String userSelection = userInput.nextLine(); // this is a method to detirmine what the user wants
		return userSelection.toUpperCase();
	}

	public String chooseItems() { // takes the userChoice() and uses it to access the map for the items
		System.out.println("Select your tasty treat: ");
		String itemPicked = userChoice();
		int stock = myMachine.getMyInventory().getStock(itemPicked);
		if (stock > 0) { // This makes sure they can only choose an item if it's stocked
			try { // This makes sure the selection is valid
				String selection = myMachine.getMyInventory().getInventoryKey(itemPicked);
				System.out.println(selection);
			} catch (NullPointerException e) {
				System.out.println("\n***Please enter a valid option***" + "\n");
				chooseItems(); // This tells them to pick a valid option and returns them to the choice menu
			}
		} else {
			System.out.println("\n***Items sold out, please choose another***" + "\n");
			chooseItems(); // This tells them an item is sold out and returns them to the choice menu
		}
		return itemPicked;
	}

	@SuppressWarnings("resource")
	public BigDecimal enterYourMoney() { // this method returns the amount of money tendered
		System.out.println("Feed money........");
		Scanner userPaymentInput = new Scanner(System.in);
		String money = userPaymentInput.nextLine();
		BigDecimal payment = new BigDecimal(money);
		return payment;

	}

	public void dispenseItem(String selection) {
		System.out.println("dispensing......\n\n");
		myMachine.getMyInventory().removeFromStock(selection);
		String sound = myMachine.getMyInventory().makeSound(selection); // gets sound
		System.out.println("heres your tasty treat.. " + sound + "\n");

	}

	public void giveChange(String selection, BigDecimal payment) { // this method does the math to return the change
		BigDecimal costOfItem = myMachine.getMyInventory().getSelectionPrice(selection);
		BigDecimal change = payment.subtract(costOfItem);
		System.out.println("heres your change: " + change);

//		BigDecimal itemPrice = myInventory.getSelectionPrice(userChoice()); // Determines the price of the item they want
//		BigDecimal userMoney = payForItems();								// sets userMoney to the amount tendered
//		BigDecimal change = itemPrice.subtract(userMoney);					// change should be the difference of amount tendered - price of item
//		System.out.println("$ " + change);									// should display the change to the console.

	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

}

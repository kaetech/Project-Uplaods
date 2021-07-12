package com.techelevator;
import com.techelevator.view.InventoryManager;
import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachine;

import java.util.Scanner;

public class VendingMachineCLI {
	//MAIN MENU CONFIG
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "End Simulation";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
	//PURCHASE MENU CONFIG
	private static final String PURCHASE_MENU_OPTION_ADD_MONEY = "Add Money";
	private static final String PURCHASE_MENU_OPTION_BUY_ITEM = "Buy Item";
	private static final String PURCHASE_MENU_OPTION_CASH_OUT = "Cash out";
	private static final String PURCHASE_MENU_OPTION_PREVIOUS_MENU = "Previous Menu";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_ADD_MONEY, PURCHASE_MENU_OPTION_BUY_ITEM, PURCHASE_MENU_OPTION_CASH_OUT, PURCHASE_MENU_OPTION_PREVIOUS_MENU};
	//LOOP CONFIG
	private static final String MAIN_LOOP = "Main";
	private static final String PURCHASE_LOOP = "Purchase";
	// DELCARE AND INSTANTIATE PRIVATE MEMBERS
	private VendingMachine vm;
	private Menu menu;
	private String vmInventoryFilePath = "vendingmachine.csv";

	//CONSTRUCTOR
	public VendingMachineCLI(Menu menu) {
		this.vm = new VendingMachine(vmInventoryFilePath);
		this.menu = menu;
	}

	// RUN METHOD
	public void run() {
		String menuLoop = MAIN_LOOP;
		Scanner userCLIInput = new Scanner(System.in);

		while (true) {
			menuLoop = MAIN_LOOP;
			System.out.println("\nWelcome to C&C Vending Machines! \nPlease choose one of the options below:");
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println("Displaying items below...");
				System.out.println();
				vm.showInventoryItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				menuLoop = PURCHASE_LOOP;
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thank you for choosing C&C Machines! Have a good day!");
				break;
			}
			// PURCHASE SUB MENU LOOP - LOOP LEVEL 0-1-0
			double userMoney = 0;
			while (menuLoop.equals(PURCHASE_LOOP)) {
				choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

				if (choice.equals(PURCHASE_MENU_OPTION_ADD_MONEY)) {
					System.out.println("\nPlease insert money (1, 2, 5, or 10):");
					String userInput = userCLIInput.nextLine();
					vm.addMoney(userInput, PURCHASE_MENU_OPTION_ADD_MONEY);
					System.out.println("Money Deposited: $" + vm.getMoneyFed());
				}
				//This is select product (from readMe)
				if (choice.equals(PURCHASE_MENU_OPTION_BUY_ITEM)) {
					System.out.println("\nPlease choose from the items available:\n");
					vm.showInventoryItems();
					System.out.println("\nEnter selection here -->");
					String userInput = userCLIInput.nextLine();
					vm.dispenseInventory(userInput);
				}
				if (choice.equals(PURCHASE_MENU_OPTION_CASH_OUT)) {
					vm.makeChange(PURCHASE_MENU_OPTION_CASH_OUT);
					menuLoop = MAIN_LOOP;
				}
				if (choice.equals(PURCHASE_MENU_OPTION_PREVIOUS_MENU)) {
					menuLoop = MAIN_LOOP;
				}
			}
		}
	}

	// PSVM PROGRAM EXECUTION ENTRY POINT
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}

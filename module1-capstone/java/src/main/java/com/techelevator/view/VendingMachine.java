package com.techelevator.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class VendingMachine {
    //vmInventoryManager is the CocaCola truck, it just loads the vmInventoryItems
    private InventoryManager vmInventoryManager;
    private double moneyFed = 0;

    private String inventoryDataFile = "";

    private SortedMap<String, List<Items>> vmSlotItems = new TreeMap<>();

    public double getMoneyFed(){
        return (this.moneyFed / 100.00);
    }

    //Constructor
    public VendingMachine (String inventoryDataFile){

        vmInventoryManager = new InventoryManager(inventoryDataFile,5);
        vmSlotItems = vmInventoryManager.getInventoryItems();

    }

    //Void method that displays inventory items
    public void showInventoryItems(){

        for(String slot: vmSlotItems.keySet()){
            List<Items> currentSlotItems = vmSlotItems.get(slot);
            if(currentSlotItems.size() > 0 ) {
                System.out.println(slot + ": " + currentSlotItems.get(0).getName() + " $" + currentSlotItems.get(0).getPrice() + " " + currentSlotItems.get(0).getType()+ " (Amount Remaining:" + currentSlotItems.size() + ") ");
            }else
            {
                System.out.println(slot + ": ** OUT OF STOCK **");
            }
        }

    }

    //Void method that dispenses items depending on remaining items and current moneyFed information
    public void dispenseInventory(String userInput) {
        userInput = userInput.toUpperCase();

        if(vmSlotItems.get(userInput).size() > 0 ) {
            if(this.moneyFed >= (int)((vmSlotItems.get(userInput)).get(0).getPrice() * 100)){

                Items dispensedItem = vmSlotItems.get(userInput).remove(0);

                Date dateOfTransactions = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
                try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("Log.txt"), true))) {
                    writer.println(formatter.format(dateOfTransactions) + " " + dispensedItem.getName() + " " + userInput +" $" + moneyFed/100 + " $" + ((moneyFed - (int)(dispensedItem.getPrice() * 100))/100.00));
                } catch (IOException e) {
                    System.out.println("Can't write to file");
                }

                this.moneyFed -= (int)(dispensedItem.getPrice() * 100);

                String selectedItemType = dispensedItem.getType();
                String dispensingMessage = "";
                switch(selectedItemType){
                    case "Chip":
                        dispensingMessage = "Crunch, Crunch, Yum!";
                        break;
                    case "Drink":
                        dispensingMessage = "Glug, Glug, Yum!";
                        break;
                    case "Gum":
                        dispensingMessage = "Chew, Chew, Yum!";
                        break;
                    case "Candy":
                        dispensingMessage = "Munch Munch, Yum!";
                        break;
                    default:
                        dispensingMessage = "Eww, what is this?";

                }


                System.out.println(dispensingMessage);
                System.out.println("Remaining Balance is: " + (this.moneyFed / 100.00));
            }
        }else {
            System.out.println(userInput + ": ** OUT OF STOCK: Please Choose Again **");
        }
    }

    //This is how our user adds money to the vending machine.
    public void addMoney(String userInput, String menuOption) {
        if (userInput.equals("1") || userInput.equals("2") || userInput.equals("5") || (userInput.equals("10"))) {
            moneyFed += Integer.parseInt(userInput) * 100;
            Date dateOfTransactions = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("Log.txt"), true))) {
                writer.println(formatter.format(dateOfTransactions) + " " + menuOption +" $" + Double.parseDouble(userInput) + " $" + (moneyFed/100.0));
            } catch (IOException e) {
                System.out.println("Can't write to file");
            }
        } else {
            System.out.println("Invalid Deposit Amount");
        }
    }

    //This is our change making method.
    public void makeChange(String menuOption) {

        double quarter = 25;
        double dime = 10;
        double nickel = 5;
        double penny = 1;
        double numberOfQuarters =0;
        double numberOfDimes = 0;
        double numberOfNickels = 0;
        double numberOfPennies = 0;
        Date dateOfTransactions = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("Log.txt"), true))) {
            writer.println(formatter.format(dateOfTransactions) + " " + menuOption +" $" + (moneyFed/100.0) + " $" + 0.00);
        } catch (IOException e) {
            System.out.println("Can't write to file");
        }

        while(moneyFed > 0) {
            if(moneyFed>= quarter) {
                numberOfQuarters++;
                moneyFed -= quarter;
            }
            else if(moneyFed >= dime) {
                numberOfDimes++;
                moneyFed -= dime;
            }
            else if(moneyFed >= nickel) {
                numberOfNickels++;
                moneyFed -= nickel;
            }
            else {
                numberOfPennies++;
                moneyFed -= penny;
            }

        }

        System.out.println("Your Change Contains: " + numberOfQuarters+ " quarter(s), " + numberOfDimes+ " dime(s), " +
                numberOfNickels+ " nickel(s), " + numberOfPennies + " pennies. \nMoney Remaining: $0.00");
    }

}

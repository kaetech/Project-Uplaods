package com.techelevator.view;

import java.util.Map;
import java.util.Scanner;

public class Items {



    private String itemSlot;
    private String name;
    private String type;
    private double price;
    private int count;

    public String getItemSlot() {
        return itemSlot;
    }

    public String getName() { return name; }

    public String getType() { return type; }

    public double getPrice() { return price; }

    public int getCount(){ return this.count;}

    public Items (String name, String type, double price){
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Items (String name, String type, double price, int count){
        this.name = name;
        this.type = type;
        this.price = price;
        this.count = count;
    }

    public Items (String itemSlot, String name, String type, double price, int count){
        this.itemSlot = itemSlot;
        this.name = name;
        this.type = type;
        this.price = price;
        this.count = count;
    }


    @Override
    public String toString() {
        return itemSlot + ": "+ name + " $" + price + " " + type + " (Amount Remaining: " + count + ")";
    }
}

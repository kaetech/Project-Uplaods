package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.SortedMap;

public class InventoryManagerTest {

    InventoryManager readTest = new InventoryManager("vendingmachine.csv", 5);
    SortedMap<String, List<Items>> sortReadTest = readTest.getInventoryItems();

    @Test
    public void readFileTest(){

        Assert.assertEquals(16, sortReadTest.size());

    }

    @Test
    public void readFileSizeTest(){

        Assert.assertEquals(5, sortReadTest.get("A1").size());

    }
}

package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

public class ItemsTest {

    @Test
    public void getItemSlotTest(){
        Items itemSlotTest = new Items("A1", "Potato Crisps", "Chip", 3.05, 5);

        String expected = "A1";
        String result = itemSlotTest.getItemSlot();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getItemPriceTest(){
        Items itemSlotTest = new Items("A1", "Potato Crisps", "Chip", 3.05, 5);

        Assert.assertEquals(3.05, itemSlotTest.getPrice(), 0.0000001);
    }

    @Test
    public void getItemCountTest(){
        Items itemSlotTest = new Items("A1", "Potato Crisps", "Chip", 3.05, 5);

        Assert.assertEquals(5, itemSlotTest.getCount());

    }

    @Test
    public void toStringTest(){

        Items itemStringTest = new Items("A1", "Potato Crisps", "Chip", 3.05, 5);

        String expected = "A1: Potato Crisps $3.05 Chip (Amount Remaining: 5)";
        String result = itemStringTest.toString();
        Assert.assertEquals(expected, result);

    }

}

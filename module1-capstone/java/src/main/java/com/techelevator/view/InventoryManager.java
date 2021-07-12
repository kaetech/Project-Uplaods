package com.techelevator.view;

import java.io.File;
import java.io.IOException;
import java.util.*;

//  ****************** THIS CLASS IS THE COCA-COLA TRUCK / HERR'S TRUCK *************************

public class InventoryManager {

    private File inputFile;
    private SortedMap<String,  List<Items>> sortedSlotItems = new TreeMap<>();

    public InventoryManager(String sourceDataFile, int defaultCount) {

        inputFile = new File(sourceDataFile);

        try (Scanner inputScanner = new Scanner(inputFile)) {
            while (inputScanner.hasNextLine()) {
                String lineInput = inputScanner.nextLine();
                String[] searchResultsArray = lineInput.replace("|", ":").split(":", 4);

                String slotNumber = searchResultsArray[0];
                String name = searchResultsArray[1];
                double price = (Double.parseDouble(searchResultsArray[2]));
                String type = searchResultsArray[3];

                Items newItem = new Items(slotNumber, name, type, price, defaultCount );
                List<Items> slotItems = new ArrayList<>();

                for( int i = 0; i < defaultCount; i++){
                    slotItems.add(newItem) ;
                }
                sortedSlotItems.put(slotNumber, slotItems);
                // Rich says, un coordinate . un-linked lists are "problematic" it's is difficult to re-associate
                // the values, an Items Object has all these attributes already, so use a list of Items
            }
        }catch(Exception ex){
            sortedSlotItems.clear();
        }
    }


    public File getInputFile() { return inputFile; }


    public SortedMap<String, List<Items>> getInventoryItems(){
        return this.sortedSlotItems;
    }

}

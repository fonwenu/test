package com.routeone.interview;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.routeone.interview.comparator.PriceComparator;
import com.routeone.interview.entity.Component;
public class StoreRegister {
 
	private static final String DOLLAR_SYMBOL = "$";
	Receipt receipt;
	DecimalFormat currFormat = new DecimalFormat("####,###,###.00");
	Map<String, String> nameToEntryMap; 
	
    public void loadInventory(File inventoryFile){
    	nameToEntryMap = new HashMap<>();
    	String line = "";
    	String delimiter = ",";
    	try{
    		BufferedReader br = new BufferedReader(new FileReader(inventoryFile));
    		while((line = br.readLine()) != null){
    			String[] entries = line.split(delimiter);
    			nameToEntryMap.put(entries[0], line);
    		}    		
    	}catch(Exception ex){
    		System.out.println("Exception occurred while loading Inventory"+ex);
    	}
    }
 
    public Receipt checkoutOrder(List<String> items) {
    	double totalPrice = 0.0;
    	List<String> filteredItems = new ArrayList<>();
    	for(String item : items){
    		filteredItems.add(nameToEntryMap.get(item));
    	}
    	
    	List<String> sortedItemsList = new ArrayList<>();
    	if(items==null || items.size()==0 || filteredItems.size() == 0){
    		throw new RuntimeException("invalid input");
    	}

    	List<Component> components = new ArrayList<>();
    	
    	for(String item : filteredItems){
    		String[] componentEntries = item.split(",");
    		components.add(new Component(componentEntries[0], Double.parseDouble(componentEntries[1]), componentEntries[2]));
    	}
    	
    	Collections.sort(components, new PriceComparator());
    	for(Component comp : components){
    		totalPrice += comp.getPrice();
    		sortedItemsList.add(comp.getName());
    	}
    	
    	receipt = new ReceiptImpl(format(totalPrice), sortedItemsList);
    	receipt.getFormattedTotal();
    	receipt.getOrderedItems();
        return receipt;
    }

	private String format(double price) {
		return DOLLAR_SYMBOL + currFormat.format(price);
	}
}
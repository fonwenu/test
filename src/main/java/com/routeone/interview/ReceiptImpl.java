package com.routeone.interview;

import java.util.List;

public class ReceiptImpl implements Receipt {

	private String formattedTotal;
	private List<String> orderedItems;
	
	public ReceiptImpl(String formattedTotal, List<String> orderedItems){
		this.formattedTotal = formattedTotal;
		this.orderedItems = orderedItems;
	}
	
	@Override
	public String getFormattedTotal() {
		return this.formattedTotal;
	}

	@Override
	public List<String> getOrderedItems() {
		return this.orderedItems;
	}

}

package com.routeone.interview.comparator;

import java.util.Comparator;

import com.routeone.interview.entity.Component;

public class PriceComparator implements Comparator<Component> {
	
	@Override
	public int compare(Component comp1, Component comp2){
		return comp2.getPrice().compareTo(comp1.getPrice());
	}
}

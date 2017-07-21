package com.routeone.interview.entity;

public class Component {

	private String name;
	private Double price;
	private String category;
	
	public Component(String name, Double price, String category){
		this.name = name;
		this.price = price;
		this.category = category;
	}
	
	public Double getPrice() {
		return price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}

package com.orderTest.model;

public class Order {
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	private String name;
	private double price;
	
	
}

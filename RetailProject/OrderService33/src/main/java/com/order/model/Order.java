package com.order.model;

public class Order {
	public Order(String oId, String itemId, int quantity, String status) {
		super();
		OId = oId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.status = status;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String OId;
    private String itemId;
    private int quantity;
    private String status;
	public String getOId() {
		return OId;
	}
	public void setOId(String oId) {
		OId = oId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
}

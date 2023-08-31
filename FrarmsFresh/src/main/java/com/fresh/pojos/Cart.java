package com.fresh.pojos;

import java.util.List;

public class Cart {
	
	private List<CartItem> items;
	private double GrandTotal;
	
	public List<CartItem> getItems() {
		return items;
	}
	
	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	public double getGrandTotal() {
		return GrandTotal;
	}
	
	public void setGrandTotal(double grandTotal) {
		GrandTotal = grandTotal;
	}
	
	public double calculateTotal(List<CartItem> items) {
		for(CartItem item : items) {
			GrandTotal+=item.getAmount();
		}
		return GrandTotal;
	}
	
}

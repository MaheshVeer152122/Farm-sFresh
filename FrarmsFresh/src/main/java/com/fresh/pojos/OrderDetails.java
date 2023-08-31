package com.fresh.pojos;

import java.io.Serializable;  
import jakarta.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetails implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "order_item", nullable = false, length = 20)
	private String orderItem;

	@Column(nullable = false, length = 10)
	private int quantity;

	@Column(nullable = false, precision = 2)
	private double amount;

	@ManyToOne
	@JoinColumn(name = "farmer_id")
	private Farmer farmer;

	@ManyToOne(optional = false)
	@JoinColumn(name = "order_id", nullable = false)
	private Orders orders;

	public OrderDetails() {
		System.out.println("OrderDetails Constructor invoked");
	}
	
	public OrderDetails(Integer id, String orderItem, int quantity, double amount, Orders orders) {
		super();
		this.id = id;
		this.orderItem = orderItem;
		this.quantity = quantity;
		this.amount = amount;
		this.orders = orders;
	}

	public String getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(String aOrderItem) {
		orderItem = aOrderItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int aQuantity) {
		quantity = aQuantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double aAmount) {
		amount = aAmount;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer aFarmer) {
		farmer = aFarmer;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders aOrders) {
		orders = aOrders;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderItem=" + orderItem + ", quantity=" + quantity + ", amount=" + amount + ", farmer="
				+ farmer + ", orders=" + orders + "]";
	}

}

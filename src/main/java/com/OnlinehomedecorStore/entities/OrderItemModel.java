package com.OnlinehomedecorStore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderItemModel")
public class OrderItemModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	private int orderId;

	private int itemId;
	private String itemName;
	private int quantity;
	private float totalCost;

	public OrderItemModel() {
	}

	public OrderItemModel(int orderId, int itemId, int quantity, float totalCost, String itemName) {
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.totalCost = totalCost;
		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
}

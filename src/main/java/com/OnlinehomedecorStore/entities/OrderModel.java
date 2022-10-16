package com.OnlinehomedecorStore.entities;

import java.time.LocalDate;
import java.time.ZoneId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderModel")
public class OrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderID;
	private int userId;
	private float totalCost;
	private String status;
	private LocalDate orderDate;

	public OrderModel() {
	}

	public OrderModel(int userId, float totalCost) {
		this.userId = userId;
		this.totalCost = totalCost;
		this.status = status = "successful";
		this.orderDate = LocalDate.now(ZoneId.of("GMT+05:30"));

	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
}

package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Long id;
	private List<Item> orderItems = new ArrayList<>();
	private Customer orderCustomer;
	private Double orderPrice;
	
	
	public Order(Long id, List<Item> orderItems, Customer orderCustomer, Double orderPrice) {
		super();
		this.id = id;
		this.orderItems = orderItems;
		this.orderCustomer = orderCustomer;
		this.orderPrice = orderPrice;
	}
	public Order(List<Item> orderItems, Customer orderCustomer, Double orderPrice) {
		super();
		this.orderItems = orderItems;
		this.orderCustomer = orderCustomer;
		this.orderPrice = orderPrice;
	}
	
	public Order(Customer orderCustomer, Double orderPrice) {
		super();
		this.orderCustomer = orderCustomer;
		this.orderPrice = orderPrice;
	}
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Item> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<Item> orderItems) {
		this.orderItems = orderItems;
	}
	public Customer getOrderCustomer() {
		return orderCustomer;
	}
	public void setOrderCustomer(Customer orderCustomer) {
		this.orderCustomer = orderCustomer;
	}
	public Double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderCustomer == null) ? 0 : orderCustomer.hashCode());
		result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
		result = prime * result + ((orderPrice == null) ? 0 : orderPrice.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderCustomer == null) {
			if (other.orderCustomer != null)
				return false;
		} else if (!orderCustomer.equals(other.orderCustomer))
			return false;
		if (orderItems == null) {
			if (other.orderItems != null)
				return false;
		} else if (!orderItems.equals(other.orderItems))
			return false;
		if (orderPrice == null) {
			if (other.orderPrice != null)
				return false;
		} else if (!orderPrice.equals(other.orderPrice))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderItems=" + orderItems + ", orderCustomer=" + orderCustomer + ", orderPrice="
				+ orderPrice + "]";
	}
	

}
package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

//@Entity
@Table(name = "invoice_details")
public class InvoiceDetail {

	//Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int amoun;
	
	private double price;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "products"
			)
	private List<Product> product;
	
	public InvoiceDetail() {
		super();
	}

	public InvoiceDetail(long id, int amoun, double price) {
		super();
		this.id = id;
		this.amoun = amoun;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAmoun() {
		return amoun;
	}

	public void setAmoun(int amoun) {
		this.amoun = amoun;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "InvoiceDetail [id=" + id + ", amoun=" + amoun + ", price=" + price + "]";
	}
	
	
}

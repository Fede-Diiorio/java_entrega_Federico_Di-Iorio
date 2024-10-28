package com.coderhouse.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice_details")
public class InvoiceDetail {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_detail_id")
	private long id;

	@Column(nullable = false)
	private int amoun;

	@Column(nullable = false)
	private double price;

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

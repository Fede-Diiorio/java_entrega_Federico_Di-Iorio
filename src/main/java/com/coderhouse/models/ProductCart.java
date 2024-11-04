package com.coderhouse.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products_carts")
public class ProductCart {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "products_carts_id")
	private long id;

	@Column(nullable = false)
	private int quantity;

	@Column(nullable = false)
	private double price;

	@ManyToOne
	@JoinColumn(name = "products_id", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "ticket_id", nullable = false)
	private Ticket ticket;

	// Constructors
	public ProductCart() {
		super();
	}

	public ProductCart(long id, int quantity, double price) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticekt) {
		this.ticket = ticekt;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "InvoiceDetail [id=" + id + ", quantity=" + quantity + ", price=" + price + "]";
	}

}

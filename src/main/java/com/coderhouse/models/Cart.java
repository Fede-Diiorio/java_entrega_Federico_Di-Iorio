package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {

	// Attributes

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@ManyToMany(mappedBy = "carts", fetch = FetchType.LAZY)
	private List<Product> products = new ArrayList<Product>();
	
	@OneToOne
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;

	// Constructor

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + "]";
	}

}

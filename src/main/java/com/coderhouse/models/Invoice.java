package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoices")
public class Invoice {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client;

	@Column
	private double total;

	private LocalDateTime created_at = LocalDateTime.now();

	@ManyToMany(mappedBy = "invoices", fetch = FetchType.EAGER)
	private List<Product> products = new ArrayList<>();

	// Constructor
	public Invoice() {
		super();
	}

	public Invoice(long id, Client client, double total, LocalDateTime created_at) {
		super();
		this.id = id;
		this.client = client;
		this.total = total;
		this.created_at = created_at;
	}

	// GET y SET
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", client=" + client + ", total=" + total + ", created_at=" + created_at + "]";
	}

}

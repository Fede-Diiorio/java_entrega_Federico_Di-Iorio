package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client;

	@Column(nullable = false)
	private double total;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(nullable = false, unique = true, updatable = false)
	private String code = UUID.randomUUID().toString().replace("-", "");

	// Constructors
	public Ticket() {
	}

	public Ticket(Client client, double total, Cart cart) {
		this.client = client;
		this.total = total;
	}

	// Getters and Setters
	public long getId() {
		return id;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", total=" + total + ", createdAt=" + createdAt + ", code=" + code + "]";
	}
}
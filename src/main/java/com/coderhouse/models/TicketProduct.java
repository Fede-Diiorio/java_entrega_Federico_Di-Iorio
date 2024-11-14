package com.coderhouse.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Modelo de TicketProduct")
@Entity
@Table(name = "tickets_products")
public class TicketProduct {

	@Schema(description = "ID del ticket", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "ticket_id", referencedColumnName = "id")
	@JsonIgnore
	private Ticket ticket;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	@JsonIgnore
	private Product product;
	
	@Column(nullable = false, length = 50)
	private String productName;
	
	@Column(nullable = false)
	private int quantity;
	
	@Column(nullable = false)
	private double unitPrice;
	
	@Column(nullable = false)
	private double subtotal;
	
}

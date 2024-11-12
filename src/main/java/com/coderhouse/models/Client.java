package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Schema(description = "Modelo de cliente")
@Entity
@Table(name = "clients")
public class Client {

	// Attributes
	@Schema(description = "ID del cliente", requiredMode = Schema.RequiredMode.REQUIRED)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Schema(description = "Nombre del cliente", requiredMode = Schema.RequiredMode.REQUIRED)
	@Column(nullable = false, length = 75)
	private String name;

	@Schema(description = "Apellido del cliente", requiredMode = Schema.RequiredMode.REQUIRED)
	@Column(nullable = false, length = 75)
	private String lastname;

	@Schema(description = "Documento del cliente", requiredMode = Schema.RequiredMode.REQUIRED)
	@Column(nullable = false, unique = true, length = 11)
	private String docnumber;

	@Schema(description = "ID del carrito del cliente", requiredMode = Schema.RequiredMode.REQUIRED)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;

	@Schema(description = "Lista de Tickets")
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Ticket> tickets = new ArrayList<Ticket>();



}
package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Modelo de Ticket")
@Entity
@Table(name = "tickets")
public class Ticket {

	// Attributes
	@Schema(description = "ID del ticket", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Schema(description = "ID del cliente")
	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	@JsonIgnore
	private Client client;

	@OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<TicketProduct> ticketProduct = new ArrayList<>();

	@Schema(description = "Total de la compra", requiredMode = Schema.RequiredMode.REQUIRED, example = "30")
	@Column(nullable = false)
	private double total;

	@Schema(description = "Fecha y horario en el que se generó el comprobante", example = "2024-11-10T07:07:02.196345")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@Schema(description = "Código único del comprobante", requiredMode = Schema.RequiredMode.REQUIRED, example = "2e7c692c409f4e6d969a9faaceafedb8")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(nullable = false, unique = true, updatable = false, length = 35)
	private String code = UUID.randomUUID().toString().replace("-", "");

}
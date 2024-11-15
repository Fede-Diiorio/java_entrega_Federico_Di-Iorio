package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
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
@Schema(description = "Modelo de Product")
@Entity
@Table(name = "products")
public class Product {

	// Attributes
	@Schema(description = "ID del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Schema(description = "Nombre del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Remera de Gryffindor")
	@Column(nullable = false, length = 50)
	private String name;

	@Schema(description = "Precio del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
	@Column(nullable = false)
	private double price;

	@Schema(description = "Link de la imagen del producto", example = "https://sitio-de-imagenes.com/imagen-requerida.jpeg")
	@Column(length = 200)
	private String image;

	@Schema(description = "Descripción del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Una remera de algodón de una de las cuatro casas de Hogwarts")
	@Column(nullable = false, length = 250)
	private String description;

	@Schema(description = "Código único del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "2ecb06e11d5c4a988f9171c669eaf56e")
	@Column(unique = true, nullable = false, length = 50)
	private String code = UUID.randomUUID().toString().replace("-", "");

	@Schema(description = "Stock del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
	@Column(nullable = false)
	private int stock;

	@Schema(description = "Nombre de la categoría del producto", example = "remera")
	@ManyToOne
	@JoinColumn(name = "category")
	private Category category;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<TicketProduct> ticketProduct = new ArrayList<TicketProduct>();

	public Product(String name, String description, String code, int stock, double price) {
		super();
		validatePrice(price);
		validateStock(stock);
		this.name = name;
		this.description = description;
		this.code = code;
		this.stock = stock;
		this.price = price;
	}

	public void setStock(int stock) {
		validateStock(stock);
		this.stock = stock;
	}

	public void setPrice(double price) {
		validatePrice(price);
		this.price = price;
	}

	// Methods
	private void validateStock(int stock) {
		if (stock < 0) {
			throw new IllegalArgumentException("El stock no puede ser inferior a cero.");
		}
	}

	private void validatePrice(double price) {
		if (price < 1) {
			throw new IllegalArgumentException("Debe establecer un precio mínimo del, al menos, $1.");
		}
	}

}
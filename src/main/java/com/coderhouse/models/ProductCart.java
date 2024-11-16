package com.coderhouse.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Schema(description = "Modelo de ProductCart")
@Entity
@Table(name = "products_carts")
public class ProductCart {

	// Attributes
	@Schema(description = "ID del producto agregado en el carrito", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "products_carts_id")
	private long id;

	@Schema(description = "Cantidad de unidades del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
	@Column(nullable = false)
	private int quantity;

	@Schema(description = "Precio del producto multiplicado por la cantidad del mismo", requiredMode = Schema.RequiredMode.REQUIRED, example = "30")
	@Column(nullable = false)
	private double price;

	@Schema(description = "ID del producto", example = "1")
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Schema(description = "ID del carrito", example = "1")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id")
	@JsonIgnore
	private Cart cart;

	public void setQuantity(int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("Cantidad ingresada inválida");
		}
		updatePrice();
		this.quantity = quantity;
	}

	public void setProduct(Product product) {
		this.product = product;
		updatePrice();
	}

	// Methods
	@PrePersist
	@PreUpdate
	public void updatePrice() {
		if (product != null) {
			this.price = product.getPrice() * quantity;
		}
	}

}
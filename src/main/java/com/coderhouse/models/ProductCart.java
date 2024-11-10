package com.coderhouse.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JoinColumn(name = "product_id")
	private Product product;

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
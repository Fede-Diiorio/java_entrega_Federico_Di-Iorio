package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Entity
@Table(name = "products")
public class Product {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false)
	private double price;

	@Column(length = 200)
	private String image;

	@Column(nullable = false, length = 250)
	private String description;

	@Column(unique = true, nullable = false, length = 50)
	private String code = UUID.randomUUID().toString().replace("-", "");

	@Column(nullable = false)
	private int stock;

	@ManyToOne
	@JoinColumn(name = "category")
	private Category category;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ProductCart> productCart = new ArrayList<>();

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
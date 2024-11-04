package com.coderhouse.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {

	// Attributes

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCart> products;


	// GET y SET
	public List<ProductCart> getProducts() {
		return products;
	}

	public void setProducts(List<ProductCart> products) {
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
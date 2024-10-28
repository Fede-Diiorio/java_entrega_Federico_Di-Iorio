package com.coderhouse.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class ProductCategory {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 30)
	private String name;

	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	Set<Product> products = new HashSet<Product>();

	// Constructors
	public ProductCategory() {
		super();
	}

	public ProductCategory(String name) {
		super();
		this.name = name;
	}

	public ProductCategory(String name, Set<Product> products) {
		super();
		this.name = name;
		this.products = products;
	}

	// GET y SET
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", name=" + name + ", products=" + products + "]";
	}

}

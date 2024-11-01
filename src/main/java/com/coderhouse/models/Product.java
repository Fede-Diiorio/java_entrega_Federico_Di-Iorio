package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(length = 200)
	private String image;

	@Column(nullable = false, length = 250)
	private String description;

	@Column(unique = true, nullable = false, length = 50)
	private String code;

	@Column(nullable = false)
	private int stock;

	@Column(nullable = false)
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "category", nullable = false)
	private ProductCategory category;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "invoice_details", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "invoice_id"))
	private List<Invoice> invoices = new ArrayList<Invoice>();

	// Constructor
	public Product() {
		super();
	}

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
	
	

	public Product(String name, String image, String description, String code, int stock, double price,
			ProductCategory category) {
		super();
		validatePrice(price);
		validateStock(stock);
		this.name = name;
		this.image = image;
		this.description = description;
		this.code = code;
		this.stock = stock;
		this.price = price;
		this.category = category;
	}

	// GET y SET
	public long getId() {
		return id;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		validateStock(stock);
		this.stock = stock;
	}

	public double getPrice() {
		return price;
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

	// ToString
	@Override
	public String toString() {
		return "Product [id=" + id + ", description=" + description + ", code=" + code + ", stock=" + stock + ", price="
				+ price + "]";
	}

}

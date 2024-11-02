package com.coderhouse.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Category {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(length = 30)
	private String slug;

	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	@JsonIgnore
	Set<Product> products = new HashSet<Product>();

	// Constructors
	public Category() {
		super();
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public Category(String name, String slug) {
		super();
		this.name = name;
		this.slug = validateSlug(slug);
	}

	// GET y SET
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = validateSlug(slug);
	}

	public long getId() {
		return id;
	}

	// Methods

	private String validateSlug(String slug) {
		if (slug == null) {
			return "sin-categoria";
		}
		return slug.toLowerCase().replace(" ", "-");
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", slug=" + slug + "]";
	}

}

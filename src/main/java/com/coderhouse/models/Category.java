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

	public Category(String name, String slug) {
		super();
		this.name = name;
		this.slug = validateSlug(slug);
	}

	public void setSlug(String slug) {
		this.slug = validateSlug(slug);
	}

	// Methods
	private String validateSlug(String slug) {
		if (slug == null) {
			return "sin-categoria";
		}
		return slug.toLowerCase().replace(" ", "-");
	}
}
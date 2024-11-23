package com.coderhouse.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
	private Long id;
	private String name;
	private double price;
	private String image;
	private String description;
	private String code;
	private int stock;
	private String category;
	
}

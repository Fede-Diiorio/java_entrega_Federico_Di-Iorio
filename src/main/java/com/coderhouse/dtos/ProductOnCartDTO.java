package com.coderhouse.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "id", "name", "unitPrice", "quantity", "totalPrice" })
public class ProductOnCartDTO {
	private Long id;
	private String name;
	private double UnitPrice;
	private int quantity;
	private double totalPrice;
}

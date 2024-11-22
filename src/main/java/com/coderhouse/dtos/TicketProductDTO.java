package com.coderhouse.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketProductDTO {

	private String productName;
	private Long productId;
	private double productPrice;
	private int quantity;
	private double subtotal;
}

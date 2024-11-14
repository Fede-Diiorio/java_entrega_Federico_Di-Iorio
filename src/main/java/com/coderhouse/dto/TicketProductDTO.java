package com.coderhouse.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketProductDTO {

	private String productName;

	private int quantity;

	private double unitPrice;

	private double subtotal;

	private long productId;

	private long ticketId;
}

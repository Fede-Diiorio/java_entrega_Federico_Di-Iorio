package com.coderhouse.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketDTO {
	private Long clientId;
	private String code; 
	private LocalDateTime createdAt;
	private List<TicketProductDTO> products = new ArrayList<TicketProductDTO>();
	private double total;
}

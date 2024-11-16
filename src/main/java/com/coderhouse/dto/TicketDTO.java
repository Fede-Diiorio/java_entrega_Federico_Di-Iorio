package com.coderhouse.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDTO {

	private double total = 0D;
	private LocalDateTime created_ad;
	private Long client_id;


	
}

package com.coderhouse.dto;

import java.time.LocalDateTime;

public class TicketDTO {

	private double total = 0D;
	private LocalDateTime created_ad = LocalDateTime.now();
	private Long client_id;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Long getClient_id() {
		return client_id;
	}

	public void setClient_id(Long client_id) {
		this.client_id = client_id;
	}

	public LocalDateTime getCreated_ad() {
		return created_ad;
	}

	
}

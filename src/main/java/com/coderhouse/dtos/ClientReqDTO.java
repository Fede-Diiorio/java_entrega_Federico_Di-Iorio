package com.coderhouse.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientReqDTO {

	private Long clientId;
	
	private String clientName;
	
	private String clientLastname;
	
	private String clientDocnumber;
	
}

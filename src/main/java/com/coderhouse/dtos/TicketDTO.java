package com.coderhouse.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo de TicketDTO")
public class TicketDTO {
	
	@Schema(description = "ID del ticket", example = "1")
	private Long id;
	
	@Schema(description = "ID del cliente", example = "1")
	private Long clientId;
	
	@Schema(description = "Código único del comprobante", requiredMode = Schema.RequiredMode.REQUIRED, example = "2e7c692c409f4e6d969a9faaceafedb8")
	private String code; 
	
	@Schema(description = "Fecha y horario en el que se generó el comprobante", example = "2024-11-10T07:07:02.196345")
	private LocalDateTime createdAt;
	
	@Schema(description = "Lista de los productos agregados en el comprobante")
	private List<TicketProductDTO> products = new ArrayList<TicketProductDTO>();
	
	@Schema(description = "Total de la compra", requiredMode = Schema.RequiredMode.REQUIRED, example = "30")
	private double total;
}

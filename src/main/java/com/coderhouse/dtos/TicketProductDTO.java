package com.coderhouse.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Modelo de TicketProductDTO")
public class TicketProductDTO {

	@Schema(description = "Nombre del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Remera de Gryffindor")
	private String productName;
	
	@Schema(description = "ID del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Long productId;
	
	@Schema(description = "Precio del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
	private double productPrice;
	
	@Schema(description = "Cantidad del producto agregado", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
	private int quantity;
	
	@Schema(description = "Total entre el precio del producto y la cantidad agregada", requiredMode = Schema.RequiredMode.REQUIRED, example = "20")
	private double subtotal;
}

package com.coderhouse.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "id", "name", "unitPrice", "quantity", "totalPrice" })
@Schema(description = "Modelo de ProductOnCartDTO")
public class ProductOnCartDTO {
	
	@Schema(description = "ID del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Long id;
	
	@Schema(description = "Nombre del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Remera de Gryffindor")
	private String name;
	
	@Schema(description = "Precio del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
	private double unitPrice;
	
	@Schema(description = "Cantidad del producto agregado", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
	private int quantity;
	
	@Schema(description = "Total entre el precio del producto y la cantidad agregada", requiredMode = Schema.RequiredMode.REQUIRED, example = "20")
	private double totalPrice;
}

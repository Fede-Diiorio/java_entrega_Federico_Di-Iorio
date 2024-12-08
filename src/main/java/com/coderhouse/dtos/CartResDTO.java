package com.coderhouse.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Modelo de CartDTO")
public class CartResDTO {
	
	@Schema(description = "ID del carrito", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
	
	@Schema(description = "Lista de los productos agregados al carrito")
    private List<ProductOnCartDTO> products;
}

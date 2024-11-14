package com.coderhouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Modelo de QuantityDTO")
public class QuantityDTO {
	
	@Schema(description = "Cantidad del producto que desea agregar", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    private int quantity;

}

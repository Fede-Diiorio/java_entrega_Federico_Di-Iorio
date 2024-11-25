package com.coderhouse.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Modelo de ClientDTO")
public class ClientDTO {

	@Schema(description = "ID del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Long id;
	
	@Schema(description = "Nombre del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Federico")
	private String name;
	
	@Schema(description = "Apellido del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Di Iorio")
	private String lastname;
	
	@Schema(description = "Documento del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "12345678")
	private String docnumber;
	
	@Schema(description = "ID del carrito del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Long cartId;
	
}

package com.coderhouse.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Modelo de CategoryDTO")
public class CategoryDTO {

	@Schema(description = "ID de la categoría", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Long id;
	
	@Schema(description = "nombre de la categoría", requiredMode = Schema.RequiredMode.REQUIRED, example = "Varitas mágicas")
	private String name;
	
	@Schema(description = "Abreviativo de la categoría", requiredMode = Schema.RequiredMode.REQUIRED, example = "varita-magica")
	private String slug;
}

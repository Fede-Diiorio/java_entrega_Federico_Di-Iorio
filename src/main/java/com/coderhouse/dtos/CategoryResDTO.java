package com.coderhouse.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Modelo de CategoryDTO")
public class CategoryResDTO {

	@Schema(description = "ID de la categoría", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Long categoryId;
	
	@Schema(description = "nombre de la categoría", requiredMode = Schema.RequiredMode.REQUIRED, example = "Varitas mágicas")
	private String categoryName;
	
	@Schema(description = "Abreviativo de la categoría", requiredMode = Schema.RequiredMode.REQUIRED, example = "varita-magica")
	private String categorySlug;
}

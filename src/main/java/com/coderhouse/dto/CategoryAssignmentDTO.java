package com.coderhouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Modelo de CategoryAssignmentDTO")
public class CategoryAssignmentDTO {

	@Schema(description = "ID del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Long productId;
	
	@Schema(description = "ID de la categía", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Long categoryId;

}

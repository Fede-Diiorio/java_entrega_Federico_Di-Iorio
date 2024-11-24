package com.coderhouse.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Modelo de ProductDTO")
public class ProductDTO {
	
	@Schema(description = "ID del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Long id;

	@Schema(description = "Nombre del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Remera de Gryffindor")
	private String name;

	@Schema(description = "Precio del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
	private Double price;

	@Schema(description = "Link de la imagen del producto", example = "https://sitio-de-imagenes.com/imagen-requerida.jpeg")
	private String image;

	@Schema(description = "Descripción del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Una remera de algodón de una de las cuatro casas de Hogwarts")
	private String description;

	@Schema(description = "Stock del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
	private Integer stock;

	@Schema(description = "ID de la categoría del producto", example = "1")
	private Long category;

}

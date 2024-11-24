package com.coderhouse.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Modelo de ProductResponseDTO")
public class ProductResponseDTO {
	@Schema(description = "ID del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Long id;

	@Schema(description = "Nombre del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Remera de Gryffindor")
	private String name;

	@Schema(description = "Precio del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
	private double price;

	@Schema(description = "Link de la imagen del producto", example = "https://sitio-de-imagenes.com/imagen-requerida.jpeg")
	private String image;

	@Schema(description = "Descripción del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Una remera de algodón de una de las cuatro casas de Hogwarts")
	private String description;

	@Schema(description = "Código único del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "2ecb06e11d5c4a988f9171c669eaf56e")
	private String code;

	@Schema(description = "Stock del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
	private int stock;

	@Schema(description = "Nombre de la categoría del producto", example = "Remeras")
	private String category;

}

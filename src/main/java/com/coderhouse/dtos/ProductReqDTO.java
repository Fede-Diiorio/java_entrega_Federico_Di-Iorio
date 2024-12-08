package com.coderhouse.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Modelo de ProductDTO")
public class ProductReqDTO {
	
	@Schema(description = "ID del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Long productId;

	@Schema(description = "Nombre del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Remera de Gryffindor")
	private String productName;

	@Schema(description = "Precio del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
	private Double productPrice;

	@Schema(description = "Link de la imagen del producto", example = "https://sitio-de-imagenes.com/imagen-requerida.jpeg")
	private String productImage;
	
	@Schema(description = "Link de la imagen del producto", example = "f6a41da776f54551819ab4a0017f42cc")
	private String productCode;

	@Schema(description = "Descripción del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Una remera de algodón de una de las cuatro casas de Hogwarts")
	private String productDescription;

	@Schema(description = "Stock del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
	private Integer productStock;

	@Schema(description = "ID de la categoría del producto", example = "1")
	private Long categoryId;

}

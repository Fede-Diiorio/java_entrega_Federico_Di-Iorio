package com.coderhouse.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coderhouse.dtos.ProductReqDTO;
import com.coderhouse.dtos.ProductResDTO;
import com.coderhouse.models.Category;
import com.coderhouse.models.Product;
import com.coderhouse.services.CategoryService;

@Component
public class ProductMapper {

	@Autowired
	private CategoryService categoryService;

	public ProductResDTO toDTO(Product product) {
		ProductResDTO dto = new ProductResDTO();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setDescription(product.getDescription());
		dto.setPrice(product.getPrice());
		dto.setStock(product.getStock());
		dto.setImage(product.getImage());
		dto.setCode(product.getCode());
		dto.setCategory(product.getCategory() != null ? product.getCategory().getName() : "sin-categoria");
		return dto;
	}

	public Product toEntity(ProductReqDTO productDTO, Product existingProduct) {
		Product product = existingProduct != null ? existingProduct : new Product();

		if (productDTO.getName() != null && !productDTO.getName().isEmpty()) {
			product.setName(productDTO.getName());
		}

		if (productDTO.getImage() != null && !productDTO.getImage().isEmpty()) {
			product.setImage(productDTO.getImage());
		}

		if (productDTO.getDescription() != null && !productDTO.getDescription().isEmpty()) {
			product.setDescription(productDTO.getDescription());
		}

		if (productDTO.getStock() != null) {
			product.setStock(productDTO.getStock());
		}

		if (productDTO.getPrice() != null) {
			product.setPrice(productDTO.getPrice());
		}

		if (productDTO.getCategory() != null) {
			Category category = categoryService.getCategoryById(productDTO.getCategory());
			if (category != null) {
				product.setCategory(category);
			}
		}

		return product;
	}


}

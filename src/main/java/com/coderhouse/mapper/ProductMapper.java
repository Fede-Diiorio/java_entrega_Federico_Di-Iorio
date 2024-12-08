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
		ProductResDTO productDTO = new ProductResDTO();
		productDTO.setProductId(product.getId());
		productDTO.setProductName(product.getName());
		productDTO.setProductDescription(product.getDescription());
		productDTO.setProductPrice(product.getPrice());
		productDTO.setProductStock(product.getStock());
		productDTO.setProductImage(product.getImage());
		productDTO.setProductCode(product.getCode());
		productDTO.setCategoryName(product.getCategory() != null ? product.getCategory().getName() : "sin-categoria");
		return productDTO;
	}

	public Product toEntity(ProductReqDTO productDTO, Product existingProduct) {
		Product product = existingProduct != null ? existingProduct : new Product();

		if (productDTO.getProductName() != null && !productDTO.getProductName().isEmpty()) {
			product.setName(productDTO.getProductName());
		}

		if (productDTO.getProductImage() != null && !productDTO.getProductImage().isEmpty()) {
			product.setImage(productDTO.getProductImage());
		}

		if (productDTO.getProductDescription() != null && !productDTO.getProductDescription().isEmpty()) {
			product.setDescription(productDTO.getProductDescription());
		}

		if (productDTO.getProductStock() != null) {
			product.setStock(productDTO.getProductStock());
		}

		if (productDTO.getProductPrice() != null) {
			product.setPrice(productDTO.getProductPrice());
		}

		if (productDTO.getCategoryId() != null) {
			Category category = categoryService.getCategoryById(productDTO.getCategoryId());
			if (category != null) {
				product.setCategory(category);
			}
		}

		return product;
	}


}

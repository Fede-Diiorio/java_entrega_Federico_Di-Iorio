package com.coderhouse.mapper;

import org.springframework.stereotype.Component;

import com.coderhouse.dtos.CategoryReqDTO;
import com.coderhouse.dtos.CategoryResDTO;
import com.coderhouse.models.Category;

@Component
public class CategoryMapper {

	public CategoryResDTO toDTO(Category category) {
		return new CategoryResDTO(category.getId(), category.getName(), category.getSlug());
	}
	
	public Category toEntity(CategoryReqDTO categoryDTO, Category existingCategory) {
		Category category = existingCategory != null ? existingCategory : new Category();
		
		if(categoryDTO.getCategoryName() != null && !categoryDTO.getCategoryName().isEmpty()) {
			category.setName(categoryDTO.getCategoryName());
		}
		
		if(categoryDTO.getCategorySlug() != null && !categoryDTO.getCategorySlug().isEmpty()) {
			category.setSlug(categoryDTO.getCategorySlug());
		}
		
		return category;
	}
}

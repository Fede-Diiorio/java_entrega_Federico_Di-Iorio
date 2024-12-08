package com.coderhouse.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryReqDTO {

	private Long categoryId;
	
	private String categoryName;
	
	private String categorySlug;
}

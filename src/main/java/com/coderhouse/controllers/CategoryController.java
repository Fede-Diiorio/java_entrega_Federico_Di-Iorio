package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dtos.CategoryDTO;
import com.coderhouse.models.Category;
import com.coderhouse.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories", description = "Operaciones relacionadas con categorías")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Operation(summary = "Mostrar todas las categorías")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categorías encontradas con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {
		try {
			List<CategoryDTO> categories = categoryService.getAll();
			return ResponseEntity.ok(categories);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Mostrar categoría por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categoría encontrada con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Categoría no encontrada según su ID", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable long id) {
		try {
			CategoryDTO category = categoryService.getById(id);
			return ResponseEntity.ok(category);

		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Crear una nueva categoría")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categoría creada con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PostMapping
	public ResponseEntity<CategoryDTO> saveCategory(@RequestBody Category category) {
		try {
			CategoryDTO createdCategory = categoryService.save(category);
			return ResponseEntity.ok(createdCategory);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Actualizar una categoría según si ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categoría actualizada con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
			@ApiResponse(responseCode = "404", description = "Categoría no encontrada según ID", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody Category category) {
		try {
			CategoryDTO updatedCategory = categoryService.update(id, category);
			return ResponseEntity.ok(updatedCategory);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Eliminar una categoría según si ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Categoría eliminada con éxito", content = @Content),
			@ApiResponse(responseCode = "404", description = "Categoría no encontrada según ID", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		try {
			categoryService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

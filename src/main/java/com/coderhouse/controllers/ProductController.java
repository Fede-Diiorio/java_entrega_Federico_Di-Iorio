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

import com.coderhouse.dtos.CategoryAssignmentDTO;
import com.coderhouse.dtos.ProductDTO;
import com.coderhouse.dtos.ProductResponseDTO;
import com.coderhouse.models.Product;
import com.coderhouse.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Operaciones relacionadas con productos")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Operation(summary = "Mostrar todos los productos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Productos encontrados con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@GetMapping
	public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
		try {
			List<ProductResponseDTO> products = productService.getAll();
			return ResponseEntity.ok(products);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Mostrar producto por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto encontrado con éxito", content = @Content),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado según su ID", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDTO> getProductByid(@PathVariable long id) {
		try {
			ProductResponseDTO product = productService.getById(id);
			return ResponseEntity.ok(product);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Crear nuevo producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto creado con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PostMapping
	public ResponseEntity<Product> saveProduct(@RequestBody ProductDTO product) {
		try {
			Product createdProduct = productService.save(product);
			return ResponseEntity.ok(createdProduct);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Asignar categoría a un producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto creado con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
			@ApiResponse(responseCode = "404", description = "ID de producto o categoría no encontrado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PostMapping("/assign-category")
	public ResponseEntity<Product> assingCategoryToProduct(@RequestBody CategoryAssignmentDTO assignmentDTO) {
		try {
			Product updatedProduct = productService.assignCategoryToProduct(assignmentDTO.getProductId(),
					assignmentDTO.getCategoryId());
			return ResponseEntity.ok(updatedProduct);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Actualizar producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto actualizado con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
			@ApiResponse(responseCode = "404", description = "ID de producto no encontrado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PutMapping("{id}")
	public ResponseEntity<Product> updateProductById(@PathVariable Long id, @RequestBody ProductDTO productDetails) {
		try {
			Product updatedProduct = productService.update(id, productDetails);
			return ResponseEntity.ok(updatedProduct);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Eliminar producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Producto eliminado con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "404", description = "ID de producto no encontrado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		try {
			productService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}

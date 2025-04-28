package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v2")
public class ProductControllerV2 {

    private List<Product> products = List.of(
        new Product(1L, "Producto A", 10.0),
        new Product(2L, "Producto B", 20.0)
    );

    @Operation(summary = "Obtener todos los productos (v2)", description = "Lista todos los productos usando DTOs en la versión 2.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Productos listados correctamente")
    })
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> index() {
        List<ProductDTO> dtos = products.stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getPrice()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Obtener producto por ID (v2)", description = "Obtiene un producto por ID utilizando DTOs en la versión 2.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> show(@PathVariable Long id) {
        Product product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        ProductDTO dto = new ProductDTO(product.getId(), product.getName(), product.getPrice());
        return ResponseEntity.ok(dto);
    }
}


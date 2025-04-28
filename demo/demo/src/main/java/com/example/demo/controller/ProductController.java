package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product(1L, "Producto A", 10.0));
        products.add(new Product(2L, "Producto B", 20.0));
    }

    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos disponibles.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente")
    })
    @GetMapping("/products")
    public ResponseEntity<List<Product>> index() {
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Obtener un producto por su ID", description = "Busca un producto usando su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> show(@PathVariable Long id) {
        Product product = products.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "Crear un nuevo producto", description = "Agrega un nuevo producto a la lista en memoria.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping("/products")
    public ResponseEntity<Product> create(@RequestBody Product newProduct) {
        products.add(newProduct);
        return ResponseEntity.status(201).body(newProduct);
    }
}

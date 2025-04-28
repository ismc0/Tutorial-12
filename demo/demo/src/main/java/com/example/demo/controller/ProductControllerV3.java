package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ProductCollection;
import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v3")
@CrossOrigin(origins = "*")
public class ProductControllerV3 {

    private List<Product> products = List.of(
        new Product(1L, "Producto A", 10.0),
        new Product(2L, "Producto B", 20.0),
        new Product(3L, "Producto C", 30.0),
        new Product(4L, "Producto D", 40.0),
        new Product(5L, "Producto E", 50.0),
        new Product(6L, "Producto F", 60.0)
    );

    @Operation(summary = "Obtener todos los productos (v3)", description = "Lista todos los productos con información adicional como nombre de tienda y enlace.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Productos listados correctamente")
    })
    @GetMapping("/products")
    public ResponseEntity<ProductCollection> index() {
        List<ProductDTO> dtos = products.stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getPrice()))
                .collect(Collectors.toList());

        ProductCollection collection = new ProductCollection(
            dtos,
            new ProductCollection.AdditionalData("Mega Store", "http://localhost:8080/api/v3/products")
        );
        return ResponseEntity.ok(collection);
    }

    @Operation(summary = "Paginar productos (v3)", description = "Devuelve una página de productos según el número de página solicitado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Productos paginados correctamente")
    })
    @GetMapping("/products/paginate")
    public ResponseEntity<ProductCollection> paginate(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 5;
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, products.size());

        List<ProductDTO> dtos;
        if (start >= products.size()) {
            dtos = List.of();
        } else {
            dtos = products.subList(start, end).stream()
                    .map(p -> new ProductDTO(p.getId(), p.getName(), p.getPrice()))
                    .collect(Collectors.toList());
        }

        ProductCollection collection = new ProductCollection(
            dtos,
            new ProductCollection.AdditionalData("Mega Store", "http://localhost:8080/api/v3/products")
        );
        return ResponseEntity.ok(collection);
    }
}

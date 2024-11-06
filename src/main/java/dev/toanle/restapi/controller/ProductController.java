package dev.toanle.restapi.controller;

import dev.toanle.restapi.model.Product;
import dev.toanle.restapi.repository.ProductRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Repository
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository _productRepository;

    // Constructor
    public ProductController(ProductRepository productRepository) {
        this._productRepository = productRepository;
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(_productRepository.save(product));
    }

    // Create batch products
    @PostMapping("/batch")
    public ResponseEntity<List<Product>> createProducts(@RequestBody List<Product> products) {
        products.forEach(_productRepository::save);
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }

    // Get all products
    @GetMapping
    public List<Product> getProducts() {
        return _productRepository.findAll();
    }

    // Get a product by id
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = _productRepository.findById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    // Update a product by id
    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = _productRepository.update(id, product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a product by id
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        _productRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}

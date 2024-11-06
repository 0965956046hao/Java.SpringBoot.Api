package dev.toanle.restapi.controller;

import dev.toanle.restapi.model.Product;
import dev.toanle.restapi.repository.ProductMemoryRepository;
import dev.toanle.restapi.repository.ProductRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Repository
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository _productRepository;
    private final ProductMemoryRepository _productMemoryRepository;

    // Constructor
    public ProductController(
            ProductRepository productRepository,
            ProductMemoryRepository productMemoryRepository) {
        this._productRepository = productRepository;
        this._productMemoryRepository = productMemoryRepository;
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = _productRepository.save(product);
        // _productMemoryRepository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // Create batch products
    @PostMapping("/batch")
    public ResponseEntity<List<Product>> createProducts(@RequestBody List<Product> products) {
        List<Product> savedProducts = _productRepository.saveAll(products); // Lưu tất cả sản phẩm trong danh sách
        return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
    }

    // Get all products
    @GetMapping
    public List<Product> getProducts() {
        return _productRepository.findAll();
    }

    // Get a product by id
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = _productRepository.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a product by id
    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Optional<Product> productOptional = _productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            _productRepository.save(product);
            return ResponseEntity.ok(product);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a product by id
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (_productRepository.existsById(id)) {
            _productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package dev.toanle.restapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import dev.toanle.restapi.model.Product;

@Repository
public class ProductMemoryRepository {
    private final List<Product> products = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // Find all products
    public List<Product> findAll() {
        return products;
    }

    // Find a product by id
    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Save a product
    public Product save(Product product) {
        product.setId(idCounter.getAndIncrement());
        products.add(product);
        return product;
    }

    // Delete a product
    public void delete(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    // Update a product
    public Product update(Long id, Product product) {
        Product existingProduct = findById(id);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
        }
        return existingProduct;
    }
}

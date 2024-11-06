package dev.toanle.restapi.repository;

import dev.toanle.restapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Spring Data JPA sẽ tự động cung cấp các phương thức CRUD cơ bản
}

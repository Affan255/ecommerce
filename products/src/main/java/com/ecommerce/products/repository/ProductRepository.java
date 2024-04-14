package com.ecommerce.products.repository;

import com.ecommerce.products.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  Product findProductByName(String name);
}

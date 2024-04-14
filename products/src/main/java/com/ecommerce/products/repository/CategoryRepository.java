package com.ecommerce.products.repository;

import com.ecommerce.products.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Category findCategoryByName(String name);
}

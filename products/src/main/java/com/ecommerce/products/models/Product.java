package com.ecommerce.products.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseEntity {
    private String name;
    private String description;
    @ManyToOne
    private Category category;
    private Double rating;
    private Integer quantity;
    private Double price;
}

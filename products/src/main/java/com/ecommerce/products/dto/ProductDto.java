package com.ecommerce.products.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
  private String name;
  private String description;
  private String categoryName;
  private Double price;
  private Integer quantity;
}

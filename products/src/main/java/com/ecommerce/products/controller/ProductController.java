package com.ecommerce.products.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  @GetMapping("/api/products")
  public String getAllProducts() {
    return "Shirt, Headphone, Mobile";
  }


}
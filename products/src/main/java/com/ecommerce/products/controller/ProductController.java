package com.ecommerce.products.controller;

import com.ecommerce.products.dto.ProductDto;
import com.ecommerce.products.exception.ProductNameExistsException;
import com.ecommerce.products.exception.ProductNotFoundException;
import com.ecommerce.products.models.Product;
import com.ecommerce.products.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping("")
  public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) throws ProductNameExistsException {
    Product product = productService.addProduct(productDto);
    return ResponseEntity.ok(product);
  }

  @PutMapping("{id}")
  public ResponseEntity<Product> updateProductById(@RequestBody ProductDto productDto, @PathVariable(name = "id") Long id) throws ProductNotFoundException{
    Product updatedProduct = productService.updateProductById(id, productDto);
    return ResponseEntity.ok(updatedProduct);
  }

  @GetMapping("{id}")
  public ResponseEntity<Product> getProductById(@PathVariable(name = "id") Long id) throws ProductNotFoundException {
    Product product = productService.getProductById(id);
    return ResponseEntity.ok(product);
  }

  @GetMapping("")
  public ResponseEntity<List<Product>> getAllProducts(){
    List<Product> products = productService.getAllProducts();
    return ResponseEntity.ok(products);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Product> deleteProductById(@PathVariable(name = "id") Long id) throws ProductNotFoundException {
    Product product = productService.deleteProductById(id);
    return ResponseEntity.ok(product);
  }

}

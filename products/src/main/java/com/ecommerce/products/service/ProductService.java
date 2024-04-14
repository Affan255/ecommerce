package com.ecommerce.products.service;

import com.ecommerce.products.dto.ProductDto;
import com.ecommerce.products.exception.ProductNameExistsException;
import com.ecommerce.products.exception.ProductNotFoundException;
import com.ecommerce.products.models.Category;
import com.ecommerce.products.models.Product;
import com.ecommerce.products.repository.CategoryRepository;
import com.ecommerce.products.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;


  private Product getProductFromProductDto(ProductDto productDto, Product product){
    if(productDto.getCategoryName()!=null){
      Category category = categoryRepository.findCategoryByName(productDto.getCategoryName());
      if(category==null) {
        category = new Category(productDto.getCategoryName(), new ArrayList<>());
        categoryRepository.save(category);
      }
      product.setCategory(category);
    }
    if(productDto.getDescription()!=null)
      product.setDescription(productDto.getDescription());
    if(productDto.getName()!=null)
      product.setName(productDto.getName());
    if(productDto.getQuantity()!=null)
      product.setQuantity(productDto.getQuantity());
    if(productDto.getPrice()!=null)
      product.setPrice(productDto.getPrice());
    return product;
  }

  public Product addProduct(ProductDto productDto) throws ProductNameExistsException {
    if(productRepository.findProductByName(productDto.getName())!=null)
      throw new ProductNameExistsException("Product with name " + productDto.getName() + " already exists!");
    Product product = getProductFromProductDto(productDto, new Product());
    product.getCategory().getProducts().add(product);
    return productRepository.save(product);
  }

  public Product getProductById(Long id) throws ProductNotFoundException {
    if(productRepository.findById(id).isEmpty())
      throw new ProductNotFoundException("Product with id " + id + " does not exist!");
    return productRepository.findById(id).get();
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product updateProductById(Long id, ProductDto productDto) throws ProductNotFoundException {
    if(productRepository.findById(id).isEmpty())
      throw new ProductNotFoundException("Product with id " + id + " does not exist!");
    Product product = productRepository.findById(id).get();
    Product updatedProduct = getProductFromProductDto(productDto, product);
    return productRepository.save(updatedProduct);
  }

  public Product deleteProductById(Long id) throws ProductNotFoundException {
    if(productRepository.findById(id).isEmpty())
      throw new ProductNotFoundException("Product with id " + id + " does not exist!");
    Product product = productRepository.findById(id).get();

    productRepository.deleteById(id);
    return product;
  }

}

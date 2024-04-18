package com.ecommerce.cart.controller;

import com.ecommerce.cart.dto.CartCreationResponseDto;
import com.ecommerce.cart.dto.CartDto;
import com.ecommerce.cart.dto.CartProductDto;
import com.ecommerce.cart.exception.CartNotFoundException;
import com.ecommerce.cart.exception.CartProductNotFoundException;
import com.ecommerce.cart.model.Cart;
import com.ecommerce.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

  @Autowired
  private CartService cartService;

  @PostMapping("/create")
  public ResponseEntity<CartCreationResponseDto> createCart(@RequestBody CartDto cartDto) {
    CartCreationResponseDto cart = cartService.createCart(cartDto);
    return ResponseEntity.ok(cart);
  }

  @PostMapping("/addProduct")
  public ResponseEntity<Cart> addProductToCart(@RequestBody CartProductDto cartProductDto) throws CartNotFoundException {
    Cart cart = cartService.addProductsToCart(cartProductDto);
    return ResponseEntity.ok(cart);
  }

  @PostMapping("/removeProduct")
  public ResponseEntity<Cart> removeProductFromCart(@RequestBody CartProductDto cartProductDto) throws CartNotFoundException, CartProductNotFoundException {
    Cart cart = cartService.removeProductsFromCart(cartProductDto);
    return ResponseEntity.ok(cart);
  }

  @GetMapping("{userId}")
  public ResponseEntity<Cart> getCartByUserId(@PathVariable("userId") Long userId) throws CartNotFoundException {
    Cart cart = cartService.getCartByUserId(userId);
    return ResponseEntity.ok(cart);
  }


}

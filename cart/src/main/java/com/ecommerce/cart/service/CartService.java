package com.ecommerce.cart.service;

import com.ecommerce.cart.dto.CartCreationResponseDto;
import com.ecommerce.cart.dto.CartDto;
import com.ecommerce.cart.dto.CartProductDto;
import com.ecommerce.cart.exception.CartNotFoundException;
import com.ecommerce.cart.exception.CartProductNotFoundException;
import com.ecommerce.cart.model.Cart;
import com.ecommerce.cart.model.CartProduct;
import com.ecommerce.cart.repository.CartProductRepository;
import com.ecommerce.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private CartProductRepository cartProductRepository;

  private CartCreationResponseDto getCartCreationResponseDto(Cart cart) {
    CartCreationResponseDto cartCreationResponseDto = new CartCreationResponseDto();
    cartCreationResponseDto.setId(cart.getId());
    cartCreationResponseDto.setUserEmail(cart.getUserEmail());
    cartCreationResponseDto.setUserId(cart.getUserId());
    return cartCreationResponseDto;
  }

  public CartCreationResponseDto createCart(CartDto cartDto) {
    Cart cart = new Cart();
    cart.setUserId(cartDto.getUserId());
    cart.setUserEmail(cartDto.getEmail());
    throw new RuntimeException("Testing exception thrown from cart microservice!");
//    Cart savedCart =  cartRepository.save(cart);
//    return getCartCreationResponseDto(savedCart);
  }

  public Cart addProductsToCart(CartProductDto cartProductDto) throws CartNotFoundException {
    Long cartId = cartProductDto.getCartId();
    Long productId = cartProductDto.getProductId();
    Long quantity = cartProductDto.getQuantity();

    if (cartRepository.findById(cartId).isEmpty()) {
      throw new CartNotFoundException("Cart with id " + cartId + " does not exist!");
    }
    Cart cart = cartRepository.findById(cartId).get();

    CartProduct cartProduct;
    cartProduct = cartProductRepository.findCartProductByProductIdAndCartId(cartId,productId);
    if(cartProduct != null)
      cartProduct.setQuantity(quantity+ cartProduct.getQuantity());
    else {
      cartProduct = new CartProduct();
      cartProduct.setProductId(productId);
      cartProduct.setQuantity(quantity);
    }
    CartProduct savedCartProduct = cartProductRepository.save(cartProduct);
    cart.getCartProducts().add(savedCartProduct);

    return cartRepository.save(cart);

  }

  public Cart removeProductsFromCart(CartProductDto cartProductDto) throws CartNotFoundException, CartProductNotFoundException {
    Long cartId = cartProductDto.getCartId();
    Long productId = cartProductDto.getProductId();
    Long quantity = cartProductDto.getQuantity();

    if (cartRepository.findById(cartId).isEmpty()) {
      throw new CartNotFoundException("Cart with id " + cartId + " does not exist!");
    }
    Cart cart = cartRepository.findById(cartId).get();

    CartProduct cartProduct;
    cartProduct = cartProductRepository.findCartProductByProductIdAndCartId(cartId,productId);
    if(cartProduct == null)
      throw new CartProductNotFoundException("Product with id " + productId + " does not exist in cart!");
    cartProduct.setQuantity(Math.max(quantity- cartProduct.getQuantity(),0));
    CartProduct savedCartProduct = cartProductRepository.save(cartProduct);
    cart.getCartProducts().add(savedCartProduct);

    return cartRepository.save(cart);

  }

  public Cart getCartByUserId(Long userId) throws CartNotFoundException {
    Cart cart = cartRepository.findCartByUserId(userId);
    if(cart == null)
      throw new CartNotFoundException("Cart for user with userId" + userId + " does not exist!");
    return cart;
  }


}

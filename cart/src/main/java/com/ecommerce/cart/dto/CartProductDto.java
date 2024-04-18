package com.ecommerce.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDto {
  private Long cartId;
  private Long productId;
  private Long quantity;
}

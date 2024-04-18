package com.ecommerce.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartCreationResponseDto {
  private Long id;
  private Long userId;
  private String userEmail;
}

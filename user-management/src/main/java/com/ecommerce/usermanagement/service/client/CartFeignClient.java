package com.ecommerce.usermanagement.service.client;

import com.ecommerce.usermanagement.dto.CartCreationResponseDto;
import com.ecommerce.usermanagement.dto.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cart")
public interface CartFeignClient {
   @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
   ResponseEntity<CartCreationResponseDto> createCart(@RequestBody CartDto cartDto);
}

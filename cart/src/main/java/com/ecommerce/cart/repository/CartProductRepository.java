package com.ecommerce.cart.repository;

import com.ecommerce.cart.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
  @Query(value = "select * from cart_product where cart_id=?1 and product_id=?2", nativeQuery = true)
  CartProduct findCartProductByProductIdAndCartId(Long cartId, Long productId);
}

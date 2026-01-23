package com.learn.SpareZone.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.SpareZone.Entities.Cart;
import com.learn.SpareZone.Entities.CartItem;
import com.learn.SpareZone.Entities.Products;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
 Optional<CartItem> findByCartAndProduct(Cart cart , Products products);
}

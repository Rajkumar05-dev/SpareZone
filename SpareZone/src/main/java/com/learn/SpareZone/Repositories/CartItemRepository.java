package com.learn.SpareZone.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.learn.SpareZone.Entities.Cart;
import com.learn.SpareZone.Entities.CartItem;
import com.learn.SpareZone.Entities.Products;
@RepositoryRestResource(path="cart-item")
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
 Optional<CartItem> findByCartAndProduct(Cart cart , Products products);
}

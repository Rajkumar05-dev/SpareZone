package com.learn.SpareZone.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.SpareZone.Entities.Cart;
import com.learn.SpareZone.Entities.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
	 Optional<Cart> findByUser(User user);

}

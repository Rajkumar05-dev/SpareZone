package com.learn.SpareZone.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.learn.SpareZone.Entities.Cart;
import com.learn.SpareZone.Entities.User;
@RepositoryRestResource(path = "cart")
public interface CartRepository extends JpaRepository<Cart, Long> {
	 Optional<Cart> findByUser(User user);

}

package com.learn.SpareZone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.learn.SpareZone.Entities.OrderItem;

@RepositoryRestResource(path="order-items") 
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}

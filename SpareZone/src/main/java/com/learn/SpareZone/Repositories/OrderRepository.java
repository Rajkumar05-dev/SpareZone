package com.learn.SpareZone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.learn.SpareZone.Entities.Order;
@RepositoryRestResource(path="orders")
public interface OrderRepository extends JpaRepository<Order, Long>  {

}

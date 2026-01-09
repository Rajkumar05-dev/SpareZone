package com.learn.SpareZone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.learn.SpareZone.Entities.Products;
@RepositoryRestResource(path = "products")

public interface ProductRepository  extends JpaRepository<Products, Long>{

}

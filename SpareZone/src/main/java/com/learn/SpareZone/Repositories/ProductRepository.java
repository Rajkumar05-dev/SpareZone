package com.learn.SpareZone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.learn.SpareZone.Entities.Products;
import java.util.List;

@RepositoryRestResource(path = "products")

public interface ProductRepository  extends JpaRepository<Products, Long>{
  
	
	List<Products> findByName(String name);
	  List<Products> findByNameContainingIgnoreCase(String name);
}

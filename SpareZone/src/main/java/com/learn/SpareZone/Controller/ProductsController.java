package com.learn.SpareZone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.SpareZone.Dtos.ProductsDto;
import com.learn.SpareZone.Entities.Products;
import com.learn.SpareZone.Repositories.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductsController {
    
	@Autowired
	private ProductRepository productRepository;
	
	
		@GetMapping("/serach/{name}")
		public ResponseEntity<List<ProductsDto>> serachProduct(@PathVariable String name){
			List<Products> serach=productRepository.findByNameContainingIgnoreCase(name);
			return new ResponseEntity<List<ProductsDto>>(HttpStatus.FOUND);
		}
		
}

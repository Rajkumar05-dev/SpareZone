package com.learn.SpareZone.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.SpareZone.Dtos.ProductsDto;

@RestController
@RequestMapping("/products")
public class ProductsController {
    
	@GetMapping("/serach/{name}")
		public ResponseEntity<List<ProductsDto>> serachProduct(@PathVariable String name){
			return new ResponseEntity<List<ProductsDto>>(HttpStatus.FOUND);
		}
	@PutMapping("/{pid}/category/{cid}")
	public ResponseEntity<ProductsDto> assignCategory(@PathVariable Long pid ,@PathVariable Long cid ){
		return new ResponseEntity<ProductsDto>(HttpStatus.OK);
		
	}
}

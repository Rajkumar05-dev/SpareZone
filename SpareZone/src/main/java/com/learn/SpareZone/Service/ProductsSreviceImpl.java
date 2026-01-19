package com.learn.SpareZone.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.SpareZone.Dtos.ProductsDto;
import com.learn.SpareZone.Entities.Products;
import com.learn.SpareZone.Repositories.ProductRepository;

@Service
public class ProductsSreviceImpl implements ProductsService {

@Autowired
	private ProductRepository productRepository;
	@Override
	public List<ProductsDto> serachProduct(String name) {
		 List<Products> products = productRepository.findByNameContainingIgnoreCase(name);
		return products.stream()
                .map(this::convertToDto)
                .toList(); 
	}
	private ProductsDto convertToDto(Products product) {
	    ProductsDto dto = new ProductsDto();
	   
	    dto.setName(product.getName());
	    dto.setPartNumber(product.getPartNumber());
	    dto.setPrice(product.getPrice());
	    dto.setStock(product.getStock());
	    dto.setDescription(product.getDescription());
	    return dto;
	}


}

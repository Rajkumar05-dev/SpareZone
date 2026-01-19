package com.learn.SpareZone.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor

public class ProductsDto {

	    private String name;
	    private String partNumber;
	    private Double price;
	    private Integer stock;
	    private String description;

}

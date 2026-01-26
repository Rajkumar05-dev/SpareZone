package com.learn.SpareZone.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
	private Long cartItemId;
 	private Long productId;
    private String productName;
    private Double price;
    
    private Double totalPrice;

}

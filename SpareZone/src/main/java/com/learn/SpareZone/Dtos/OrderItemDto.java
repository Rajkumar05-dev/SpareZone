package com.learn.SpareZone.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderItemDto {
	private Long productId;
	private String productName;
	private int quantity;
	private double price;
	private double totalPrice;
}

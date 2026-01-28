package com.learn.SpareZone.Dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDto {
	private Long orderId;
	private String userEmail;
	private List<OrderItemDto> items;
	private double totalAmount;
	private String status;
}

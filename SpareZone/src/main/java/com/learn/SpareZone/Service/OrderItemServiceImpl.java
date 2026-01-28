package com.learn.SpareZone.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.SpareZone.Dtos.OrderItemDto;
import com.learn.SpareZone.Entities.Order;
import com.learn.SpareZone.Entities.OrderItem;
import com.learn.SpareZone.Repositories.OrderItemRepository;
import com.learn.SpareZone.Repositories.OrderRepository;


@Service
public class OrderItemServiceImpl implements  OrderItemService {
	
	@Autowired 
	
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public List<OrderItemDto> getItemByOrderId(Long orderId) {
	 Order order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("order not found"));
		return order.getOrderItems().stream().map(this::mapToDto).toList();
	}
	private OrderItemDto mapToDto(OrderItem oi) {
		OrderItemDto dto = new OrderItemDto(); 
		dto.setProductId(oi.getProduct().getProductId());
		dto.setProductName(oi.getProduct().getName());
		dto.setQuantity(oi.getQuantity());
		dto.setPrice(oi.getPrice());
		dto.setTotalPrice(oi.getTotalPrice());
		return dto;
		
	}

}

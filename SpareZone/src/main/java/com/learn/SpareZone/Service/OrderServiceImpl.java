package com.learn.SpareZone.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.SpareZone.Dtos.OrderDto;
import com.learn.SpareZone.Dtos.OrderItemDto;
import com.learn.SpareZone.Entities.Cart;
import com.learn.SpareZone.Entities.Order;
import com.learn.SpareZone.Entities.OrderItem;
import com.learn.SpareZone.Entities.OrderStatus;
import com.learn.SpareZone.Entities.User;
import com.learn.SpareZone.Repositories.CartRepository;
import com.learn.SpareZone.Repositories.OrderRepository;
import com.learn.SpareZone.Repositories.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartRepository cartRepository;

	@Override
	public OrderDto placeOrder(String email) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user Not found"));
		Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("user not found"));
		Order order = new Order();
		order.setUser(user);
		order.setStatus(OrderStatus.PLACED);
		order.setCreatedAt(LocalDateTime.now());
		List<OrderItem> orderItems = cart.getCartItems().stream().map(ci -> {
			OrderItem oi = new OrderItem();
			oi.setOrder(order);
			oi.setProduct(ci.getProduct());
			oi.setQuantity(ci.getQuantity());
			oi.setPrice(ci.getProduct().getPrice());
			oi.setTotalPrice(ci.getQuantity() * ci.getProduct().getPrice());
			return oi;
		}).toList();

		double totalAmount = orderItems.stream().mapToDouble(OrderItem::getTotalPrice).sum();
		order.setOrderItems(orderItems);
		order.setTotalAmount(totalAmount);

		Order savedOrder = orderRepository.save(order);
		cart.getCartItems().clear();
		cartRepository.save(cart);
		;
		return mapToDto(savedOrder);
	}

	private OrderDto mapToDto(Order order) {
		OrderDto dto = new OrderDto();
		dto.setOrderId(order.getId());
		dto.setUserEmail(order.getUser().getEmail());
		dto.setStatus(order.getStatus().name());
		dto.setTotalAmount(order.getTotalAmount());

		List<OrderItemDto> items = order.getOrderItems().stream().map(oi -> {
			OrderItemDto i = new OrderItemDto();
			i.setProductId(oi.getProduct().getProductId());
			i.setProductName(oi.getProduct().getName());
			i.setQuantity(oi.getQuantity());
			i.setPrice(oi.getPrice());
			i.setTotalPrice(oi.getTotalPrice());
			return i;
		}).toList();

		dto.setItems(items);
		return dto;
	}

}

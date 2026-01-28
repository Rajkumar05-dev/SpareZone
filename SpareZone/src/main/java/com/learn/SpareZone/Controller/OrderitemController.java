package com.learn.SpareZone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.SpareZone.Dtos.OrderItemDto;
import com.learn.SpareZone.Service.OrderItemService;

@RestController
@RequestMapping("/order-items")
public class OrderitemController {
	

	    @Autowired
	    
	    private OrderItemService orderItemService;

	    @GetMapping("/{orderId}")
	    public List<OrderItemDto> getItemsByOrder(@PathVariable Long orderId) {
	        return orderItemService.getItemByOrderId(orderId);
	    
	}

}

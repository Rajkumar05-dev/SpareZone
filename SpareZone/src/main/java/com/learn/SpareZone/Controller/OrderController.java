package com.learn.SpareZone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.SpareZone.Dtos.OrderDto;
import com.learn.SpareZone.Service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<OrderDto> placeOrder(@RequestParam String email) {
        return ResponseEntity.ok(orderService.placeOrder(email));
    }
}

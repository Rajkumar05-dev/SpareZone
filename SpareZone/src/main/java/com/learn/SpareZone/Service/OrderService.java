package com.learn.SpareZone.Service;

import com.learn.SpareZone.Dtos.OrderDto;

public interface OrderService {

	OrderDto placeOrder(String email);

}

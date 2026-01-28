package com.learn.SpareZone.Service;

import java.util.List;

import com.learn.SpareZone.Dtos.OrderItemDto;

public interface OrderItemService {
     List<OrderItemDto> getItemByOrderId(Long orderId);
}

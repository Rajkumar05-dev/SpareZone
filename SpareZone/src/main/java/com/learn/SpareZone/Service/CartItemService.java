package com.learn.SpareZone.Service;

import com.learn.SpareZone.Dtos.AddToCartDto;
import com.learn.SpareZone.Dtos.CartDto;

public interface CartItemService {
	CartDto addtoCart(AddToCartDto addToCartDto);

	CartDto getCartByUser(String id);

	CartDto updateQuantity(Long id, int quantity);

	CartDto removeItem(Long id);

}

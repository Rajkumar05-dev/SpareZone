package com.learn.SpareZone.Service;

import com.learn.SpareZone.Dtos.AddToCartDto;
import com.learn.SpareZone.Dtos.CartDto;
import com.learn.SpareZone.Entities.Cart;

public interface CartService {
	CartDto addtoCart(AddToCartDto addToCartDto, String email);

}

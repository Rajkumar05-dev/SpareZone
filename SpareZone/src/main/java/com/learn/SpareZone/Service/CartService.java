package com.learn.SpareZone.Service;

import com.learn.SpareZone.Dtos.CartDto;
import com.learn.SpareZone.Entities.Cart;

public interface CartService {
   Cart getorCreate(String id);
    Cart addtoCart(String id,Long productId, int quantity);
    Cart removefromCart(String id, Long productId );
    Cart clearCart(String id );
    CartDto  getCartDto(String id);
}

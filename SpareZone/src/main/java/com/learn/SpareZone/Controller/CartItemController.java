package com.learn.SpareZone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learn.SpareZone.Dtos.AddToCartDto;
import com.learn.SpareZone.Dtos.CartDto;
import com.learn.SpareZone.Service.CartItemService;

@RestController
@RequestMapping("/cart-item")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    // ✅ Add to cart
   

    // ✅ Get cart by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDto> getCartByUser(@PathVariable String userId) {
        CartDto cartDto = cartItemService.getCartByUser(userId);
        return ResponseEntity.ok(cartDto);
    }

    // ✅ Update quantity
    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartDto> updateQuantity(
            @PathVariable Long cartItemId,
            @RequestParam int quantity) {

        CartDto cartDto = cartItemService.updateQuantity(cartItemId, quantity);
        return ResponseEntity.ok(cartDto);
    }

    // ✅ Remove item
    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<CartDto> removeItem(@PathVariable Long cartItemId) {
        CartDto cartDto = cartItemService.removeItem(cartItemId);
        return ResponseEntity.ok(cartDto);
    }
}

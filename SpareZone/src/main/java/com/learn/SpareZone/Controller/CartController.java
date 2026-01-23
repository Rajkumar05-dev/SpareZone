package com.learn.SpareZone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learn.SpareZone.Entities.Cart;
import com.learn.SpareZone.Service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // 🔹 Get cart (auto create if not exists)
    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getorCreate(userId));
    }

    // 🔹 Add product to cart
    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(
            @RequestParam String userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        return ResponseEntity.ok(
                cartService.addtoCart(userId, productId, quantity)
        );
    }

    // 🔹 Remove product from cart
    @DeleteMapping("/remove")
    public ResponseEntity<Cart> removeFromCart(
            @RequestParam String userId,
            @RequestParam Long productId) {

        return ResponseEntity.ok(
                cartService.removefromCart(userId, productId)
        );
    }

    // 🔹 Clear full cart
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Cart> clearCart(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.clearCart(userId));
    }
}


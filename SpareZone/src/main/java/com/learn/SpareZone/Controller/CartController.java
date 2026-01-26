package com.learn.SpareZone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learn.SpareZone.Dtos.AddToCartDto;
import com.learn.SpareZone.Dtos.CartDto;
import com.learn.SpareZone.Entities.Cart;
import com.learn.SpareZone.Service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @PostMapping("/add")
    public ResponseEntity<CartDto> addToCart(
            @RequestBody AddToCartDto addToCartDto,
            @RequestParam String email) {

        return ResponseEntity.ok(cartService.addtoCart(addToCartDto, email));
    }

}

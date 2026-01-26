package com.learn.SpareZone.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.SpareZone.Dtos.AddToCartDto;
import com.learn.SpareZone.Dtos.CartDto;
import com.learn.SpareZone.Dtos.CartItemDto;
import com.learn.SpareZone.Entities.Cart;
import com.learn.SpareZone.Entities.CartItem;
import com.learn.SpareZone.Entities.Products;
import com.learn.SpareZone.Entities.User;
import com.learn.SpareZone.Repositories.CartItemRepository;
import com.learn.SpareZone.Repositories.CartRepository;
import com.learn.SpareZone.Repositories.ProductRepository;
import com.learn.SpareZone.Repositories.UserRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartDto addtoCart(AddToCartDto addToCartDto, String email) {

        // 1️⃣ User
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2️⃣ Product
        Products product = productRepository.findById(addToCartDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 3️⃣ Get or create Cart
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        // 4️⃣ Get or create CartItem
        CartItem cartItem = cartItemRepository
                .findByCartAndProduct(cart, product)
                .orElse(null);

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(addToCartDto.getQuantity());

            // 🔥 IMPORTANT: maintain bidirectional relation
            cart.getCartItems().add(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + addToCartDto.getQuantity());
        }

        cartItemRepository.save(cartItem);

        // 5️⃣ ENTITY → DTO mapping
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getId());
        cartDto.setUserId(user.getId());

        List<CartItemDto> itemDtos = cart.getCartItems().stream().map(ci -> {
            CartItemDto dto = new CartItemDto();
            dto.setCartItemId(ci.getId());
            dto.setProductId(ci.getProduct().getProductId());
            dto.setProductName(ci.getProduct().getName());
            dto.setPrice(ci.getProduct().getPrice());

            double totalPrice = ci.getQuantity() * ci.getProduct().getPrice();
            dto.setTotalPrice(totalPrice);

            return dto;
        }).toList();

        double totalAmount = itemDtos.stream()
                .mapToDouble(CartItemDto::getTotalPrice)
                .sum();

        cartDto.setItems(itemDtos);
        cartDto.setTotalAmount(totalAmount);

        return cartDto;
    }
}

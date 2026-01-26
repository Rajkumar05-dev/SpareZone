package com.learn.SpareZone.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public CartDto addtoCart(AddToCartDto addToCartDto) {
		User user = userRepository.findById(addToCartDto.getUserId())
				.orElseThrow(() -> new RuntimeException("user not found"));
		Products product = productRepository.findById(addToCartDto.getProductId())
				.orElseThrow(() -> new RuntimeException("Product not found"));

		Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
			Cart newCart = new Cart();
			newCart.setUser(user);
			newCart.setCartItems(new ArrayList<>());
			return cartRepository.save(newCart);
		});
		Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart, product);
		if (existingItem.isPresent()) {
			CartItem cartItem = existingItem.get();
			cartItem.setQuantity(cartItem.getQuantity() + addToCartDto.getQuantity());
			cartItemRepository.save(cartItem);
		} else {
			CartItem newItem = new CartItem();
			newItem.setCart(cart);
			newItem.setProduct(product);
			newItem.setQuantity(addToCartDto.getQuantity());
			cartItemRepository.save(newItem);
			cart.getCartItems().add(newItem);

		}
		return mapToCartDto(cart);
	}

	@Override
	public CartDto getCartByUser(String id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("cart not found"));
		return mapToCartDto(cart);
	}

	@Override
	public CartDto updateQuantity(Long id, int quantity) {
		CartItem cartItem = cartItemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("cart item not found"));
		cartItem.setQuantity(quantity);
		cartItemRepository.save(cartItem);
		return mapToCartDto(cartItem.getCart());
	}

	@Override
	public CartDto removeItem(Long id) {
		CartItem cartItem = cartItemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cart item not found"));
		Cart cart = cartItem.getCart();
		cartItemRepository.delete(cartItem);
		return mapToCartDto(cart);
	}

	private CartDto mapToCartDto(Cart cart) {
		CartDto cartDto = new CartDto();
		cartDto.setCartId(cart.getId());
		List<CartItemDto> items = cart.getCartItems().stream().map(item->{
			CartItemDto dto = new CartItemDto();
			dto.setCartItemId(item.getId());
			dto.setProductId(item.getProduct().getProductId());
			dto.setProductName(item.getProduct().getName());
			dto.setPrice(item.getProduct().getPrice());
			dto.setTotalPrice(item.getProduct().getPrice()*item.getQuantity());
			
			return dto;
		}).toList();
		cartDto.setItems(items);
		double total=items.stream().mapToDouble(CartItemDto::getTotalPrice).sum();
		cartDto.setTotalAmount(total);

		return cartDto;
	}

}

package com.learn.SpareZone.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private CartRepository cartRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	 private CartItemRepository cartItemRepository;
	
@Override
	public Cart getorCreate(String id) {
	     User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Id not found"));
	     if(user.getCart() != null) {
	    	 return user.getCart();
	     }
	      Cart cart = new  Cart();
	      cart.setUser(user);
	      cart.setCartItems(new ArrayList<>());
	      Cart savedCart = cartRepository.save(cart);
		return savedCart;
	}

	@Override
	public Cart addtoCart(String id, Long productId, int quantity) {
	    User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException(" Id not  found ") );
	    Products  product= productRepository.findById(productId).orElseThrow(()->new RuntimeException("productId not found") );
	    Cart cart= getorCreate(id);
	    for(CartItem item :cart. getCartItems()) {
	    	if(item.getProduct().getProductId().equals(productId)) {
	    		item.setQuantity(item.getQuantity()+quantity);
	    		return cart;
	    	}
	    	
	    }
	   CartItem item = new CartItem();
	    item.setCart(cart);
	    item.setProduct(product);
	    item.setQuantity(quantity);
	    cart.getCartItems().add(item);
	    cartItemRepository.save(item);
		return cart ;
	}
	
	@Override
	public Cart removefromCart(String id, Long productId) {
	   Cart cart = getorCreate(id);
	  CartItem removeitem=null;
	  for(CartItem item:cart.getCartItems()) {
		  if(item.getProduct().getProductId().equals(productId)) {
			  removeitem=item;
			  break;
			  
		  }
	  }
	  if(removeitem != null) {
		  cart.getCartItems().remove(removeitem);
		  cartItemRepository.delete(removeitem);
	  }
	  
		return cart;
	}

	@Override
	public Cart clearCart(String id) {
		Cart cart = getorCreate(id); 
		cartItemRepository.deleteAll(cart.getCartItems());
		return cart;
	}

	@Override
	public CartDto getCartDto(String id) {
		  Cart cart = getorCreate(id);

	    // convert CartItems to CartItemDto
	    List<CartItemDto> items = cart.getCartItems().stream().map(ci -> {
	        CartItemDto dto = new CartItemDto();
	        dto.setProductId(ci.getProduct().getProductId());
	        dto.setProductName(ci.getProduct().getName());
	        dto.setQuantity(ci.getQuantity());
	        return dto;
	    }).toList();

	    CartDto cartDto = new CartDto();
	    cartDto.setCartItems(items);
	    return cartDto;
	}

	

	

}

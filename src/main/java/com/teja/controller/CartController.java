package com.teja.controller;

import com.teja.model.Cart;
import com.teja.model.CartItem;
import com.teja.request.AddCartItemRequest;
import com.teja.request.UpdateCartItemRequest;
import com.teja.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add an item to the cart
    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.addItemToCart(req, jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    // Update quantity of a cart item
    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody UpdateCartItemRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    // Remove an item from the cart
    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        Cart cart = cartService.removeItemFromCart(id, jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Clear all items from the cart
    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String jwt) throws Exception {
        Cart cart = cartService.clearCart(jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Get the user's cart
    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws Exception {
        Cart cart = cartService.findCartByUserId(jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Find cart by ID
    @GetMapping("/cart/{id}")
    public ResponseEntity<Cart> findCartById(@PathVariable Long id) throws Exception {
        Cart cart = cartService.findCartById(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Calculate cart total
    @GetMapping("/cart/{id}/total")
    public ResponseEntity<Long> calculateCartTotal(@PathVariable Long id) throws Exception {
        Cart cart = cartService.findCartById(id);
        Long total = cartService.calculateCartTotals(cart);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}
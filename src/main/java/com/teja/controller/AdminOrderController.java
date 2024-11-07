package com.teja.controller;

import com.teja.model.Order;
import com.teja.service.OrderService;
import com.teja.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestaurantService restaurantService;

    // Endpoint to get all orders for a specific restaurant with optional status filter
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getRestaurantOrders(@PathVariable Long restaurantId, @RequestParam(required = false) String orderStatus) throws Exception {
        List<Order> orders = orderService.getRestaurantsOrder(restaurantId, orderStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Endpoint to update the status of an order
    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) throws Exception {
        Order updatedOrder = orderService.updateOrder(orderId, status);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    // Endpoint to get details of a specific order by order ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable Long orderId) throws Exception {
        Order order = orderService.findOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}

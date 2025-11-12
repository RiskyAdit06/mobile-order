package com.example.mobileorder.controller;

import com.example.mobileorder.dto.OrderRequestDto;
import com.example.mobileorder.model.Order;
import com.example.mobileorder.service.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // region insert order
    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDto orderRequest) {
        try {
            Order order = orderService.placeOrder(
                    orderRequest.getCustomer(),
                    orderRequest.getAddress(),
                    orderRequest.getItems());
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // region get order
    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    // region get order by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        try {
            Order order = orderService.getOrderById(id);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
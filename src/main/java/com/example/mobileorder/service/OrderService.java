package com.example.mobileorder.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mobileorder.dto.OrderItemDto;
import com.example.mobileorder.model.Order;
import com.example.mobileorder.model.OrderItem;
import com.example.mobileorder.model.Product;
import com.example.mobileorder.repository.OrderRepository;
import com.example.mobileorder.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // region insert order
    public Order placeOrder(String customer, String address, List<OrderItemDto> itemsDto) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setAddress(address);

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemDto itemDto : itemsDto) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(
                            () -> new RuntimeException("Product with id=" + itemDto.getProductId() + " not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDto.getQuantity());

            BigDecimal itemTotal = product.getPrice().multiply(new BigDecimal(itemDto.getQuantity()));
            orderItem.setPrice(itemTotal);

            items.add(orderItem);
            total = total.add(itemTotal);
        }

        order.setItems(items);
        order.setTotal(total);

        return orderRepository.save(order);
    }

    // region get all order
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // region get by id
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order with id=" + id + " not found"));
    }

}
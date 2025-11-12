package com.example.mobileorder.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderRequestDto {
    private String customer;
    private String address;
    private List<OrderItemDto> items;
}

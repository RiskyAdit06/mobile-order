package com.example.mobileorder.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long typeId;
    private String typeName;
}

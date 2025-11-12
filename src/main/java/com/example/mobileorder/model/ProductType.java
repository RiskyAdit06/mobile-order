package com.example.mobileorder.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}

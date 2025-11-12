package com.example.mobileorder.repository;

import com.example.mobileorder.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

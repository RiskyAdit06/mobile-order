package com.example.mobileorder.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.mobileorder.dto.ProductDto;
import com.example.mobileorder.model.Product;
import com.example.mobileorder.model.ProductType;
import com.example.mobileorder.repository.ProductRepository;
import com.example.mobileorder.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor

public class ProductService {
    private final ProductRepository productRepository;
    private final ProductTypeRepository typeRepository;

    // region get all
    public List<Product> findAll(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return productRepository.findAll(paging).getContent();
    }

    // region insert
    public Product save(ProductDto dto) {
        ProductType type = typeRepository.findById(dto.getTypeId())
                .orElseThrow(() -> new RuntimeException("ProductType not found"));
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setType(type);
        return productRepository.save(product);
    }

    // region update
    public Product update(Long id, ProductDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (dto.getName() != null)
            product.setName(dto.getName());
        if (dto.getPrice() != null)
            product.setPrice(dto.getPrice());
        if (dto.getTypeId() != null) {
            ProductType type = typeRepository.findById(dto.getTypeId())
                    .orElseThrow(() -> new RuntimeException("ProductType not found"));
            product.setType(type);
        }
        return productRepository.save(product);
    }

    // region delete
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product with id " + id + " not found.");
        }
        productRepository.deleteById(id);
    }

}

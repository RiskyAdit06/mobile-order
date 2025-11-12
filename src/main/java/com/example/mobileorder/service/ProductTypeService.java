package com.example.mobileorder.service;

import com.example.mobileorder.dto.ProductTypeDto;
import com.example.mobileorder.model.ProductType;
import com.example.mobileorder.repository.ProductTypeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

    private final ProductTypeRepository typeRepository;

    // region Get ProductType
    public List<ProductType> getAllTypes() {
        return typeRepository.findAll();
    }

    // region Create ProductType
    public ProductType createType(ProductTypeDto dto) {
        ProductType type = new ProductType();
        type.setName(dto.getName());
        return typeRepository.save(type);
    }

    // region Update ProductType
    public ProductType updateType(Long id, ProductTypeDto dto) {
        ProductType existing = typeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductType not found id=" + id));
        existing.setName(dto.getName());
        return typeRepository.save(existing);
    }

    // region Delete ProductType
    public void deleteType(Long id) {
        if (!typeRepository.existsById(id)) {
            throw new RuntimeException("ProductType with id " + id + " not found.");
        }
        typeRepository.deleteById(id);
    }
}
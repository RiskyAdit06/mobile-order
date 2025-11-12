package com.example.mobileorder.controller;

import com.example.mobileorder.dto.ProductTypeDto;
import com.example.mobileorder.model.ProductType;
import com.example.mobileorder.service.ProductTypeService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/types")
@RequiredArgsConstructor
public class ProductTypeController {

    private final ProductTypeService typeService;

    // region get all
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<ProductType> types = typeService.getAllTypes();
            return ResponseEntity.ok(types);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // region insert
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductTypeDto dto) {
        try {
            ProductType type = typeService.createType(dto);
            return ResponseEntity.ok(type);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // region update
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductTypeDto dto) {
        try {
            ProductType type = typeService.updateType(id, dto);
            return ResponseEntity.ok(type);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // region delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            typeService.deleteType(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "ProductType with id " + id + " has been deleted successfully.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
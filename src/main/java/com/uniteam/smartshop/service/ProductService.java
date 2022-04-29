package com.uniteam.smartshop.service;

import com.uniteam.smartshop.domain.Product;
import com.uniteam.smartshop.payload.Status;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getAll();
    Product get(UUID id);
    Status delete(UUID id);
}

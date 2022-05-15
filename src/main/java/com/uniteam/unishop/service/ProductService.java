package com.uniteam.unishop.service;

import com.uniteam.unishop.domain.Product;
import com.uniteam.unishop.payload.Status;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product get(Long id);
    Status delete(Long id);
}

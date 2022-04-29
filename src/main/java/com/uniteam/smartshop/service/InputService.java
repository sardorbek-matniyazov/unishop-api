package com.uniteam.smartshop.service;

import com.uniteam.smartshop.payload.ProductDto;
import com.uniteam.smartshop.payload.Status;

public interface InputService {
    Status getAll();
    Status get(Long id);
    Status create(ProductDto dto);
    Status update(Long id, ProductDto dto);
    Status delete(Long id);
}

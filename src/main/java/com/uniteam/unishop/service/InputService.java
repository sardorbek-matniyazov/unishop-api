package com.uniteam.unishop.service;

import com.uniteam.unishop.payload.ProductDto;
import com.uniteam.unishop.payload.Status;

public interface InputService {
    Status getAll();
    Status get(Long id);
    Status create(ProductDto dto);
    Status update(Long id, ProductDto dto);
    Status delete(Long id);
}

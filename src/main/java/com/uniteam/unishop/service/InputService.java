package com.uniteam.unishop.service;

import com.uniteam.unishop.domain.Input;
import com.uniteam.unishop.payload.ProductDto;
import com.uniteam.unishop.payload.Status;

import java.util.List;

public interface InputService {
    List<Input> getAll();
    Status get(Long id);
    Status create(ProductDto dto);
    Status delete(Long id);
}

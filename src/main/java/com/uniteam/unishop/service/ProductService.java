package com.uniteam.unishop.service;

import com.uniteam.unishop.domain.Product;
import com.uniteam.unishop.payload.ProductDto;
import com.uniteam.unishop.payload.Status;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product get(Long id);
    Status delete(Long id);

    List<Product> getByName(String name);

    Status update(Long id, ProductDto dto);
}

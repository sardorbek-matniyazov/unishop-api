package com.uniteam.unishop.service;

import com.uniteam.unishop.domain.Category;
import com.uniteam.unishop.payload.Status;

import java.util.List;


public interface CategoryService {
    List<Category> getAll();
    Status get(Integer id);
    Status create(Category dto);
    Status update(Integer id, Category dto);
    Status delete(Integer id);
}

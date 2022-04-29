package com.uniteam.smartshop.service;

import com.uniteam.smartshop.domain.Category;
import com.uniteam.smartshop.payload.Status;

import java.util.List;


public interface CategoryService {
    List<Category> getAll();
    Category get(Integer id);
    Status create(Category dto);
    Status update(Integer id, Category dto);
    Status delete(Integer id);
}

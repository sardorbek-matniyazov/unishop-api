package com.uniteam.smartshop.repository;

import com.uniteam.smartshop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
}

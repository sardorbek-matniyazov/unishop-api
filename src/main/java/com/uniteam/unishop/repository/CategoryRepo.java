package com.uniteam.unishop.repository;

import com.uniteam.unishop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
}

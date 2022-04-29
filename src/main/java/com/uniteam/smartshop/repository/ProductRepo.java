package com.uniteam.smartshop.repository;

import com.uniteam.smartshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);
}

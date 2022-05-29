package com.uniteam.unishop.repository;

import com.uniteam.unishop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    @Query("select p from Product p where lower(p.name) like %?1%")
    List<Product> findAllByNameLike(String name);
}

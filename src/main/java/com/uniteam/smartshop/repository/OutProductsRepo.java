package com.uniteam.smartshop.repository;

import com.uniteam.smartshop.domain.OutputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutProductsRepo extends JpaRepository<OutputProduct, Long> {
}

package com.uniteam.unishop.repository;

import com.uniteam.unishop.domain.OutputProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OutProductsRepo extends JpaRepository<OutputProduct, Long> {
    @Query(
            value = "SELECT SUM(cost) FROM output_product WHERE output_id = ?1",
            nativeQuery = true
    )
    double sumOfAllProductsFromOutput(Long output_id);

    List<OutputProduct> findAllByOutput_Id(Long output_id);
}

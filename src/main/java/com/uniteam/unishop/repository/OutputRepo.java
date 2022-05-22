package com.uniteam.unishop.repository;

import com.uniteam.unishop.domain.Output;
import com.uniteam.unishop.domain.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutputRepo extends JpaRepository<Output, Long> {
    List<Output> findAllByClient_Id(Long id);

    List<Output> findAllByStatus(PaymentStatus debt);
}

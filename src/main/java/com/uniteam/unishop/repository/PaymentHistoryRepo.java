package com.uniteam.unishop.repository;

import com.uniteam.unishop.domain.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentHistoryRepo extends JpaRepository<PaymentHistory, Long> {
    List<PaymentHistory> findAllByOutput_Client_Id(Long id);
}

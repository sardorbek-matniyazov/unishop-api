package com.uniteam.smartshop.repository;

import com.uniteam.smartshop.domain.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepo extends JpaRepository<PaymentHistory, Long> {
}

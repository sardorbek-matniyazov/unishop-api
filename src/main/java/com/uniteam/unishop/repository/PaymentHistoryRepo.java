package com.uniteam.unishop.repository;

import com.uniteam.unishop.domain.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface PaymentHistoryRepo extends JpaRepository<PaymentHistory, Long> {
    List<PaymentHistory> findAllByOutput_Client_Id(Long id);

    List<PaymentHistory> findAllByOutput_Id(Long id);

    @Query(
            value = "SELECT SUM(amount) FROM output WHERE created_date >= ?1 AND created_date <= ?2",
            nativeQuery = true
    )
    Double sumOfMonth(Timestamp open, Timestamp exit);
}

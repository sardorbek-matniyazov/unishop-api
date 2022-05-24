package com.uniteam.unishop.repository;

import com.uniteam.unishop.domain.Output;
import com.uniteam.unishop.domain.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface OutputRepo extends JpaRepository<Output, Long> {
    List<Output> findAllByClient_Id(Long id);

    List<Output> findAllByStatus(PaymentStatus debt);

    @Query(
            value = "SELECT * FROM output WHERE created_date >= ?1 AND created_date <= ?2 order by created_date desc",
            nativeQuery = true
    )
    List<Output> findAllByOpenInAndExit(Timestamp openIn, Timestamp exitIn);

}

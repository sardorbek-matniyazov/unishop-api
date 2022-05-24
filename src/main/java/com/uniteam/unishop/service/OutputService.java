package com.uniteam.unishop.service;

import com.uniteam.unishop.domain.Output;
import com.uniteam.unishop.domain.OutputProduct;
import com.uniteam.unishop.domain.PaymentHistory;
import com.uniteam.unishop.payload.OutputDto;
import com.uniteam.unishop.payload.Status;

import java.sql.Timestamp;
import java.util.List;

public interface OutputService {
    List<Output> getAll();
    Status get(Long id);
    Status create(OutputDto dto);
    Status delete(Long id);

    List<OutputProduct> getProducts(Long id);

    List<PaymentHistory> getPayments(Long id);

    List<Output> getByDate(Long open, Long exit);
}

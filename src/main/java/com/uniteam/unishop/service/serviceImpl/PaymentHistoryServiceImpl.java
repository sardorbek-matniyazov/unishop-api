package com.uniteam.unishop.service.serviceImpl;// Created by Sardorbek Matniyazov on 5/25/2022 at 12:12 PM

import com.uniteam.unishop.repository.PaymentHistoryRepo;
import com.uniteam.unishop.service.PaymentHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@lombok.Data
@Service
@RequiredArgsConstructor
public class PaymentHistoryServiceImpl implements PaymentHistoryService {
    private final PaymentHistoryRepo repo;

    @Override
    public List<Double> getSumLastSevenMonths() {
        Timestamp now = new Timestamp(new Date().getYear(), new Date().getMonth() + 1, 1, 0 , 0, 0, 0);
        Timestamp next = new Timestamp(new Date().getYear(), new Date().getMonth() + 2, 1, 0 , 0, 0, 0);
        return new ArrayList<>(
                Arrays.asList(
                        sumOfOneMonth(now, next),
                        sumOfOneMonth(now, next),
                        sumOfOneMonth(now, next),
                        sumOfOneMonth(now, next),
                        sumOfOneMonth(now, next),
                        sumOfOneMonth(now, next),
                        sumOfOneMonth(now, next)
                )
        );
    }

    private Double sumOfOneMonth(Timestamp first, Timestamp next){
        return repo.sumOfMonth(setMonth(first), setMonth(next));
    }

    private Timestamp setMonth(Timestamp time) {
        time.setMonth(time.getMonth() - 1);
        return time;
    }
}

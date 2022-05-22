package com.uniteam.unishop.service;// Created by Sardorbek Matniyazov on 5/21/2022 at 11:27 AM

import com.uniteam.unishop.domain.Output;
import com.uniteam.unishop.payload.PayDto;
import com.uniteam.unishop.payload.Status;

import java.util.List;

public interface DebtService {
    List<Output> getAll();
    Output get(Long id);

    Status pay(PayDto dto);
}

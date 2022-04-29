package com.uniteam.smartshop.service;

import com.uniteam.smartshop.payload.InputCashDto;
import com.uniteam.smartshop.payload.Status;

public interface InputCashService {
    Status getAll();
    Status get(Long id);
    Status create(InputCashDto dto);
    Status update(Long id, InputCashDto dto);
    Status delete(Long id);
}

package com.uniteam.smartshop.service;

import com.uniteam.smartshop.payload.OutputDto;
import com.uniteam.smartshop.payload.Status;

public interface OutputService {
    Status getAll();
    Status get(Long id);
    Status create(OutputDto dto);
    Status update(Long id, OutputDto dto);
    Status delete(Long id);
}

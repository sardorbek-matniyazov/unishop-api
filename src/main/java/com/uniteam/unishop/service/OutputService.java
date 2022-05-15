package com.uniteam.unishop.service;

import com.uniteam.unishop.payload.OutputDto;
import com.uniteam.unishop.payload.Status;

public interface OutputService {
    Status getAll();
    Status get(Long id);
    Status create(OutputDto dto);
    Status update(Long id, OutputDto dto);
    Status delete(Long id);
}

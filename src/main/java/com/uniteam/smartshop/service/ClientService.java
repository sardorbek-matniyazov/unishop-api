package com.uniteam.smartshop.service;

import com.uniteam.smartshop.domain.Client;
import com.uniteam.smartshop.payload.ClientDto;
import com.uniteam.smartshop.payload.Status;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    List<Client> getAll();
    Client get(UUID id);
    Status create(ClientDto dto);
    Status update(UUID id, ClientDto dto);
    Status delete(UUID id);
}

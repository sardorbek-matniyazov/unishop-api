package com.uniteam.unishop.service;

import com.uniteam.unishop.domain.Client;
import com.uniteam.unishop.domain.Output;
import com.uniteam.unishop.domain.PaymentHistory;
import com.uniteam.unishop.payload.Status;

import java.util.List;

public interface ClientService {
    List<Client> getAll();
    Client get(Long id);
    Status create(Client dto);
    Status update(Long id, Client dto);
    Status delete(Long id);

    List<Output> getSales(Long id);

    List<PaymentHistory> getPaymentHistory(Long id);

    List<Client> getByName(String name);
}

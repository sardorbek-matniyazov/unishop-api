package com.uniteam.smartshop.service.serviceImpl;

import com.uniteam.smartshop.domain.Client;
import com.uniteam.smartshop.domain.InputCash;
import com.uniteam.smartshop.payload.InputCashDto;
import com.uniteam.smartshop.payload.Status;
import com.uniteam.smartshop.repository.ClientRepo;
import com.uniteam.smartshop.repository.InputCashRepo;
import com.uniteam.smartshop.service.InputCashService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InputCashServiceImpl implements InputCashService {

    private final InputCashRepo repo;
    private final ClientRepo clientRepo;

    @Override
    public Status getAll() {
        Status dataEntity = Status.DATA_ENTITY;
        dataEntity.setBody(repo.findAll());
        return dataEntity;
    }

    @Override
    public Status get(Long id) {
        if (!repo.existsById(id))
            return Status.ITEM_NOT_FOUND;
        Status dataEntity = Status.DATA_ENTITY;
        dataEntity.setBody(repo.findById(id).orElse(null));
        return dataEntity;
    }

    @Override
    public Status create(InputCashDto dto) {
        Optional<Client> byId = clientRepo.findById(dto.getClientId());
        if (byId.isPresent()){
            Client client = byId.get();
            if (dto.getCashAmount() != null)
                client.setBalance(client.getBalance() + dto.getCashAmount());
            if (dto.getCardAmount() != null)
                client.setBalance(client.getBalance() + dto.getCardAmount());
            client = clientRepo.save(client);
            InputCash inputCash = dto.toEntity(client);
            Status successSaved = Status.SUCCESS_SAVED;
            successSaved.setBody(inputCash);
            return successSaved;
        }

        return Status.CLIENT_NOT_FOUND;
    }

    @Override
    public Status update(Long id, InputCashDto dto) {
        return null;
    }

    @Override
    public Status delete(Long id) {
        return null;
    }
}

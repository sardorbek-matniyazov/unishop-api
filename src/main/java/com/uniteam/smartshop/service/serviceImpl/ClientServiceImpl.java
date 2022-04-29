package com.uniteam.smartshop.service.serviceImpl;

import com.uniteam.smartshop.domain.Client;
import com.uniteam.smartshop.payload.ClientDto;
import com.uniteam.smartshop.payload.Status;
import com.uniteam.smartshop.repository.ClientRepo;
import com.uniteam.smartshop.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepo repo;

    @Override
    public List<Client> getAll() {
        return repo.findAll();
    }

    @Override
    public Client get(UUID id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Status create(ClientDto dto) {
        if (repo.existsByFullName(dto.getFullName()))
            return Status.CLIENT_ALREADY_EXIST;
        Client client = dto.toEntity();
        Client save = repo.save(client);
        Status successSaved = Status.SUCCESS_SAVED;
        successSaved.setBody(save);
        return successSaved;
    }

    @Override
    public Status update(UUID id, ClientDto dto) {
        if (repo.existsByFullNameAndIdNot(dto.getFullName(), id))
            return Status.CLIENT_ALREADY_EXIST;
        Client client = dto.toEntity(id);
        Client update = repo.save(client);
        Status successUpdate = Status.SUCCESS_UPDATE;
        successUpdate.setBody(update);
        return successUpdate;
    }

    @Override
    public Status delete(UUID id) {
        /*TODO: i do make better client delete*/
        return null;
    }
}

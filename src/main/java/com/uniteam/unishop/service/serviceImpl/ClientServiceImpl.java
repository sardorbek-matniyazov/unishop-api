package com.uniteam.unishop.service.serviceImpl;

import com.uniteam.unishop.domain.Client;
import com.uniteam.unishop.domain.Output;
import com.uniteam.unishop.domain.PaymentHistory;
import com.uniteam.unishop.payload.Status;
import com.uniteam.unishop.repository.ClientRepo;
import com.uniteam.unishop.repository.OutputRepo;
import com.uniteam.unishop.repository.PaymentHistoryRepo;
import com.uniteam.unishop.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepo repo;
    private final OutputRepo outputRepo;
    private final PaymentHistoryRepo paymentHistoryRepo;

    @Override
    public List<Client> getAll() {
        return repo.findAll();
    }

    @Override
    public Client get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Status create(Client dto) {
        if (repo.existsByFullName(dto.getFullName()))
            return Status.CLIENT_ALREADY_EXIST;
        if (repo.existsByPhoneNumber(dto.getPhoneNumber()))
            return Status.PHONE_NUMBER_TAKEN;
        Client save = repo.save(dto);
        Status successSaved = Status.SUCCESS_SAVED;
        successSaved.setBody(save);
        return successSaved;
    }

    @Override
    public Status update(Long id, Client dto) {
        if (repo.existsByFullNameAndIdNot(dto.getFullName(), id))
            return Status.CLIENT_ALREADY_EXIST;
        if (repo.existsByPhoneNumberAndIdNot(dto.getPhoneNumber(), id))
            return Status.PHONE_NUMBER_TAKEN;
        Client update = repo.save(dto);
        Status successUpdate = Status.SUCCESS_UPDATE;
        successUpdate.setBody(update);
        return successUpdate;
    }

    @Override
    public Status delete(Long id) {
        /*TODO: i do make better client delete*/
        return null;
    }

    @Override
    public List<Output> getSales(Long id) {
        return outputRepo.findAllByClient_Id(id);
    }

    @Override
    public List<PaymentHistory> getPaymentHistory(Long id) {
        return paymentHistoryRepo.findAllByOutput_Client_Id(id);
    }

    @Override
    public List<Client> getByName(String name) {
        return repo.findAllByFullNameLike(name);
    }

}

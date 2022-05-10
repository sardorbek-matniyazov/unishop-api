package com.uniteam.smartshop.service.serviceImpl;

import com.uniteam.smartshop.domain.*;
import com.uniteam.smartshop.domain.enums.PaymentStatus;
import com.uniteam.smartshop.domain.enums.PaymentType;
import com.uniteam.smartshop.payload.OutProducts;
import com.uniteam.smartshop.payload.OutputDto;
import com.uniteam.smartshop.payload.Status;
import com.uniteam.smartshop.repository.*;
import com.uniteam.smartshop.service.OutputService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutputServiceImpl implements OutputService {

    private final OutputRepo repo;
    private final ClientRepo clientRepo;
    private final ProductRepo productRepo;
    private final PaymentHistoryRepo paymentHistoryRepo;

    @Override
    public Status getAll() {
        Status dataEntity = Status.DATA_ENTITY;
        dataEntity.setBody(repo.findAll());
        return dataEntity;
    }

    @Override
    public Status get(Long id) {
        Optional<Output> byId = repo.findById(id);
            Status dataEntity = Status.DATA_ENTITY;
            dataEntity.setBody(byId.orElse(null));
        return dataEntity;
    }

    @Transactional
    @Override
    public Status create(OutputDto dto) {
        Optional<Client> byId = clientRepo.findById(dto.getClientId());
        if (byId.isPresent()) {
            Client client = byId.get();
            Output output = new Output();
            output.setClient(client);
            Set<OutputProduct> outputProducts = setProducts(dto.getProducts(), output);
            if (outputProducts.isEmpty())
                return Status.PRODUCTS_LIST_IS_NULL;
            output.setProducts(outputProducts);
            if (dto.getCostDebt() != 0){
                output.setStatus(PaymentStatus.DEBT);
                output.setDebtAmount((-1) * dto.getCostDebt());
                output.setExpiredDate(dto.getExpiredDate());
            }else {
                output.setStatus(PaymentStatus.PAID);
            }
            output = repo.save(output);

            if (dto.getCostCard() != 0) {
                paymentHistoryRepo.save(
                        new PaymentHistory(
                                dto.getCostCard(),
                                PaymentType.CARD,
                                output
                        )
                );
            }
            if (dto.getCostCash() != 0) {
                paymentHistoryRepo.save(
                        new PaymentHistory(
                                dto.getCostCash(),
                                PaymentType.CASH,
                                output
                        )
                );
            }
            Status successSaved = Status.SUCCESS_SAVED;
            successSaved.setBody(output);
            return successSaved;
        }
        return Status.CLIENT_NOT_FOUND;
    }

    private Set<OutputProduct> setProducts(List<OutProducts> products, Output output) {
            Set<OutputProduct> outputProducts = new HashSet<>();

        products.forEach(product -> {
            try {
                Product realProduct = productRepo.getById(product.getProductId());

                realProduct.setQuantity(realProduct.getQuantity() - product.getQuantity());
                productRepo.save(realProduct);

                outputProducts.add(
                        new OutputProduct(
                                realProduct,
                                product.getCost(),
                                product.getQuantity(),
                                product.getCost() - realProduct.getPrice(),
                                output
                        )
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return outputProducts;
    }

    @Override
    public Status update(Long id, OutputDto dto) {
        return null;
    }

    @Override
    public Status delete(Long id) {
        return null;
    }
}
// May 2 20:45 Matniyazov Sardor
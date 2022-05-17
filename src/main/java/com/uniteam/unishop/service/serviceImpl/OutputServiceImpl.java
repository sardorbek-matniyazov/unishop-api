package com.uniteam.unishop.service.serviceImpl;

import com.uniteam.unishop.domain.*;
import com.uniteam.unishop.domain.enums.PaymentStatus;
import com.uniteam.unishop.domain.enums.PaymentType;
import com.uniteam.unishop.payload.OutProducts;
import com.uniteam.unishop.payload.OutputDto;
import com.uniteam.unishop.payload.Status;
import com.uniteam.unishop.repository.*;
import com.uniteam.unishop.service.OutputService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutputServiceImpl implements OutputService {

    private final OutputRepo repo;
    private final ClientRepo clientRepo;
    private final ProductRepo productRepo;
    private final PaymentHistoryRepo paymentHistoryRepo;
    private final OutProductsRepo outProductsRepo;

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
                output.setExpiredDate(new Timestamp(dto.getExpiredDate()));
            }else {
                output.setStatus(PaymentStatus.PAID);
            }
            double amount = 0;
            Set<PaymentHistory> histories = new HashSet<>();
            if (dto.getCostCard() != 0) {
                histories.add(
                        new PaymentHistory(
                                dto.getCostCard(),
                                PaymentType.CARD,
                                output
                        )
                );
                amount += dto.getCostCard();
            }
            if (dto.getCostCash() != 0) {
                histories.add(
                        new PaymentHistory(
                                dto.getCostCash(),
                                PaymentType.CASH,
                                output
                        )
                );
                amount += dto.getCostCash();
            }
            amount += output.getDebtAmount();

            output.setAmount(amount);
            output.setPaymentHistories(histories);

            output = repo.save(output);

            return Status.setBodyToStatus(Status.SUCCESS_SAVED, output);
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
                                product.getQuantity() * (product.getCost() - realProduct.getPrice()),
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
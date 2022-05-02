package com.uniteam.smartshop.service.serviceImpl;

import com.uniteam.smartshop.domain.*;
import com.uniteam.smartshop.payload.OutProducts;
import com.uniteam.smartshop.payload.OutputDto;
import com.uniteam.smartshop.payload.Status;
import com.uniteam.smartshop.repository.*;
import com.uniteam.smartshop.service.OutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OutputServiceImpl implements OutputService {

    private final OutputRepo repo;
    private final ClientRepo clientRepo;
    private final OutProductsRepo outProductsRepo;
    private final ProductRepo productRepo;
    private final ProfitRepo profitRepo;
    private final DebtRepo debtRepo;

    @Override
    public Status getAll() {
        Status dataEntity = Status.DATA_ENTITY;
        dataEntity.setBody(repo.findAll());
        return dataEntity;
    }

    @Override
    public Status get(Long id) {
        Optional<Output> byId = repo.findById(id);
        if (byId.isPresent()) {
            Status dataEntity = Status.DATA_ENTITY;
            dataEntity.setBody(byId.get());
        }
        return Status.ITEM_NOT_FOUND;
    }

    @Transactional
    @Override
    public Status create(OutputDto dto) {
        Optional<Client> byId = clientRepo.findById(dto.getClientId());
        if (byId.isPresent()) {
            Client client = byId.get();
            Output output = new Output();
            output.setClient(client);
            Set<OutputProduct> outputProducts = setProducts(dto.getProducts());
            output.setProducts(outputProducts);
            if (dto.getCostCard() != null) {
                output.setCostCash(dto.getCostCard());
                output.setByCard(true);
            }
            if (dto.getCostCash() != null) {
                output.setCostCard(dto.getCostCash());
                output.setByCash(true);
            }
            if (dto.getCostDebt() < 0) {
                output.setByDebt(true);
                output.setDebt(
                        debtRepo.save(
                                new Debt(
                                        client,
                                        (-1) * dto.getCostDebt(),
                                        dto.getExpiredDate(),
                                        output
                                )
                        )
                );
            }
            repo.save(output);
        }
        return Status.CLIENT_NOT_FOUND;
    }

    private Set<OutputProduct> setProducts(List<OutProducts> products) {
        Set<OutputProduct> outputProducts = new HashSet<>();

        products.forEach(product -> {
            try {
                Integer quantity = product.getQuantity();
                Product realProduct = productRepo.getById(product.getProductId());

                Profit profit = profitRepo.save(
                        new Profit(
                                outProductsRepo.save(
                                        new OutputProduct(
                                                realProduct,
                                                product.getCost(),
                                                quantity
                                        )
                                ),
                                realProduct.getPrice() - product.getCost()
                        )
                );

                realProduct.setQuantity(realProduct.getQuantity() - product.getQuantity());
                productRepo.save(realProduct);

                outputProducts.add(profit.getProduct());
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
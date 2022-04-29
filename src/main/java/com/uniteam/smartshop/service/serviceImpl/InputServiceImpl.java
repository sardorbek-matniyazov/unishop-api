package com.uniteam.smartshop.service.serviceImpl;

import com.uniteam.smartshop.domain.Input;
import com.uniteam.smartshop.domain.Product;
import com.uniteam.smartshop.payload.ProductDto;
import com.uniteam.smartshop.payload.Status;
import com.uniteam.smartshop.repository.InputRepo;
import com.uniteam.smartshop.repository.ProductRepo;
import com.uniteam.smartshop.service.InputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InputServiceImpl implements InputService {
    private final InputRepo repo;
    private final ProductRepo productRepo;

    @Override
    public Status getAll() {
        Status status = Status.DATA_ENTITY;
        status.setBody(repo.findAll());
        return status;
    }

    @Override
    public Status get(Long id) {
        Status status = Status.DATA_ENTITY;
        status.setBody(repo.findById(id).orElse(null));
        return status;
    }

    @Override
    public Status create(ProductDto dto) {
        Optional<Product> byName = productRepo.findByName(dto.getName());
        if (byName.isPresent()) {
            Product product = byName.get();
            product.setQuantity(product.getQuantity() + dto.getQuantity());
            product = productRepo.save(product);
            Input input = new Input();
            input.setProduct(product);
            input.setProductName(dto.getName());
            input.setBrand(product.getBrand());
            input.setWholesalePrice(product.getWholesalePrice());
            input.setMinimumPrice(product.getMinimumPrice());
            input.setMaximumPrice(product.getMaximumPrice());
            repo.save(input);
        }
        Product product = dto.toEntity();
        Input input = dto.toInput(product);
        input = repo.save(input);
        Status successSaved = Status.SUCCESS_SAVED;
        successSaved.setBody(input);
        return successSaved;
    }

    @Override
    public Status update(Long id, ProductDto dto) {
        /*TODO: i do make better input update */
        return null;
    }

    @Override
    public Status delete(Long id) {
        /*TODO: i do make better input delete */
        return null;
    }
}

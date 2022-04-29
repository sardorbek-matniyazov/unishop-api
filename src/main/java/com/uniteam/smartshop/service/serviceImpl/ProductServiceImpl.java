package com.uniteam.smartshop.service.serviceImpl;

import com.uniteam.smartshop.domain.Product;
import com.uniteam.smartshop.payload.Status;
import com.uniteam.smartshop.repository.ProductRepo;
import com.uniteam.smartshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo repo;

    @Override
    public List<Product> getAll() {
        return repo.findAll();
    }

    @Override
    public Product get(UUID id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Status delete(UUID id) {
        try {
            repo.deleteById(id);
            return Status.DELETED;
        }catch (Exception e){
            e.printStackTrace();
            return Status.CANT_DELETE;
        }
    }
}

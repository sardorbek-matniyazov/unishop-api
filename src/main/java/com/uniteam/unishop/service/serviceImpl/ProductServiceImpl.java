package com.uniteam.unishop.service.serviceImpl;

import com.uniteam.unishop.domain.Product;
import com.uniteam.unishop.payload.ProductDto;
import com.uniteam.unishop.payload.Status;
import com.uniteam.unishop.repository.ProductRepo;
import com.uniteam.unishop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo repo;

    @Override
    public List<Product> getAll() {
        return repo.findAll();
    }

    @Override
    public Product get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Status delete(Long id) {
        try {
            repo.deleteById(id);
            return Status.DELETED;
        } catch (Exception e) {
            e.printStackTrace();
            return Status.CANT_DELETE;
        }
    }

    @Override
    public List<Product> getByName(String name) {
        return repo.findAllByNameLike(name);
    }

    @Override
    public Status update(Long id, ProductDto dto) {
        Optional<Product> byId = repo.findById(id);
        if (byId.isPresent()) {
            Product product = byId.get();
            product.setQuantity(dto.getQuantity());
            product.setMaximumPrice(dto.getMaximumPrice());
            product.setMinimumPrice(dto.getMinimumPrice());
            product.setPrice(dto.getPrice());
            repo.save(product);
            return Status.SUCCESS_UPDATE;
        }
        return Status.ITEM_NOT_FOUND;
    }
}

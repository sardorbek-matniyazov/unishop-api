package com.uniteam.unishop.service.serviceImpl;

import com.uniteam.unishop.domain.Product;
import com.uniteam.unishop.payload.Status;
import com.uniteam.unishop.repository.ProductRepo;
import com.uniteam.unishop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        }catch (Exception e){
            e.printStackTrace();
            return Status.CANT_DELETE;
        }
    }

    @Override
    public List<Product> getByName(String name) {
        return repo.findAllByNameLike(name);
    }
}

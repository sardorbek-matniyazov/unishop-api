package com.uniteam.smartshop.service.serviceImpl;

import com.uniteam.smartshop.domain.Category;
import com.uniteam.smartshop.payload.Status;
import com.uniteam.smartshop.repository.CategoryRepo;
import com.uniteam.smartshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo repo;

    public List<Category> getAll() {
        return repo.findAll();
    }

    @Override
    public Category get(Integer id) {
        Optional<Category> byId = repo.findById(id);
        return byId.orElse(null);
    }

    @Override
    public Status create(Category dto) {
        if (repo.existsByName(dto.getName()))
            return Status.CATEGORY_ALREADY_EXISTS;
        Category save = repo.save(dto);
        Status successSaved = Status.SUCCESS_SAVED;
        successSaved.setAny(save);
        return successSaved;
    }

    @Override
    public Status update(Integer id, Category dto) {
        /* TODO: i do make better that category update */
        return null;
    }

    @Override
    public Status delete(Integer id) {
        /* TODO: i do make better that category delete */
        return null;
    }

}

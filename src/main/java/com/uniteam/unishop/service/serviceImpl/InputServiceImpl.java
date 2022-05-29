package com.uniteam.unishop.service.serviceImpl;

import com.uniteam.unishop.domain.Category;
import com.uniteam.unishop.domain.Input;
import com.uniteam.unishop.domain.Product;
import com.uniteam.unishop.payload.ProductDto;
import com.uniteam.unishop.payload.Status;
import com.uniteam.unishop.repository.CategoryRepo;
import com.uniteam.unishop.repository.InputRepo;
import com.uniteam.unishop.repository.ProductRepo;
import com.uniteam.unishop.service.InputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InputServiceImpl implements InputService {
    private final InputRepo repo;
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public List<Input> getAll() {
        return repo.findAll();
    }

    @Override
    public Status get(Long id) {
        Status status = Status.DATA_ENTITY;
        status.setBody(repo.findById(id).orElse(null));
        return status;
    }

    @Override
    public Status create(ProductDto dto) {

        // TODO : yana product qo'shilsa senasin o'zgertiw

        Optional<Category> byCategoryId = categoryRepo.findById(dto.getCategoryId());
        if (byCategoryId.isPresent()) {
            Category category = byCategoryId.get();
            Optional<Product> byName = productRepo.findByName(dto.getName());
            if (byName.isPresent()) {
                Product product = byName.get();
                product.setQuantity(product.getQuantity() + dto.getQuantity());
                product.setCategory(category);
                product = productRepo.save(product);

                Input input = new Input(
                        product,
                        dto.getQuantity()
                );
                Status successSaved = Status.SUCCESS_SAVED;
                successSaved.setBody(repo.save(input));
                return successSaved;
            }
            Product product = dto.toEntity();
            product.setCategory(category);
            Input input = dto.toInput(product);
            input = repo.save(input);
            Status successSaved = Status.SUCCESS_SAVED;
            successSaved.setBody(input);
            return successSaved;
        }
        return Status.CATEGORY_NOT_FOUND;
    }

    @Override
    public Status delete(Long id) {
        /*TODO: i do make better input delete */
        return null;
    }
}

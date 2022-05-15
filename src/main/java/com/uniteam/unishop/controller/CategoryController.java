package com.uniteam.unishop.controller;

import com.uniteam.unishop.domain.Category;
import com.uniteam.unishop.payload.Status;
import com.uniteam.unishop.service.serviceImpl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl service;

    @GetMapping(value = "/all")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<?> get(@PathVariable Integer id){
        Status item = service.get(id);
        return ResponseEntity.status(item.getStatus()).body(item.getBody());
    }

    @PostMapping(value = "/create")
    public HttpEntity<?> create(@Valid @RequestBody Category dto){
        Status create = service.create(dto);
        return ResponseEntity.status(create.getStatus()).body(create);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

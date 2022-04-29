package com.uniteam.smartshop.controller;

import com.uniteam.smartshop.domain.Product;
import com.uniteam.smartshop.payload.Status;
import com.uniteam.smartshop.service.serviceImpl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

import static com.uniteam.smartshop.controller.CategoryController.handleValidationExceptions;

@RestController
@RequestMapping(value = "/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl service;

    @GetMapping(value = "/all")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<?> get(@PathVariable UUID id){
        Product item = service.get(id);
        return item != null ?
                ResponseEntity.ok(item) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping(value = "/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id){
        Status delete = service.delete(id);
        return ResponseEntity.status(delete.getStatus()).body(delete);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static Map<String, String> handleExceptions(MethodArgumentNotValidException e){
        return handleValidationExceptions(e);
    }
}

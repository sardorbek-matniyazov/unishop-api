package com.uniteam.unishop.controller;

import com.uniteam.unishop.domain.Product;
import com.uniteam.unishop.payload.ProductDto;
import com.uniteam.unishop.payload.Status;
import com.uniteam.unishop.service.serviceImpl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.uniteam.unishop.controller.CategoryController.handleValidationExceptions;

@RestController
@RequestMapping(value = "/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl service;

    @GetMapping(value = "/all")
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<?> get(@PathVariable Long id) {
        Product item = service.get(id);
        return item != null ?
                ResponseEntity.ok(item) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping(value = "/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        Status delete = service.delete(id);
        return ResponseEntity.status(delete.getStatus()).body(delete);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static Map<String, String> handleExceptions(MethodArgumentNotValidException e) {
        return handleValidationExceptions(e);
    }

    @GetMapping(value = "/get")
    public HttpEntity<?> getWithName(@RequestParam(value = "name") String name) {
        List<Product> item = service.getByName(name.toLowerCase());
        return ResponseEntity.ok(item);
    }

    @PutMapping(value = "/{id}")
    public HttpEntity<?> update(@RequestBody ProductDto dto, @PathVariable Long id) {
        Status delete = service.update(id, dto);
        return ResponseEntity.status(delete.getStatus()).body(delete);
    }
}

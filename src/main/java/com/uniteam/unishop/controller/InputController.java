package com.uniteam.unishop.controller;

import com.uniteam.unishop.payload.ProductDto;
import com.uniteam.unishop.payload.Status;
import com.uniteam.unishop.service.serviceImpl.InputServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.uniteam.unishop.controller.CategoryController.handleValidationExceptions;

@RestController
@RequestMapping(value = "/api/v1/input")
@RequiredArgsConstructor
public class InputController {
    private final InputServiceImpl service;
    @GetMapping(value = "/all")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<?> get(@PathVariable Long id){
        Status item = service.get(id);
        return ResponseEntity.status(item.getStatus()).body(item);
    }

    @PostMapping(value = "/create")
    public HttpEntity<?> create(@Valid @RequestBody ProductDto dto){
        Status create = service.create(dto);
        return ResponseEntity.status(create.getStatus()).body(create);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static Map<String, String> handleExceptions(MethodArgumentNotValidException e){
        return handleValidationExceptions(e);
    }

}

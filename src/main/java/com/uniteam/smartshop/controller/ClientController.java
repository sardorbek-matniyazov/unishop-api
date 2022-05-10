package com.uniteam.smartshop.controller;

import com.uniteam.smartshop.domain.Client;
import com.uniteam.smartshop.payload.ClientDto;
import com.uniteam.smartshop.payload.Status;
import com.uniteam.smartshop.service.serviceImpl.ClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

import static com.uniteam.smartshop.controller.CategoryController.handleValidationExceptions;

@RestController
@RequestMapping(value = "/api/v1/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientServiceImpl service;

    @GetMapping(value = "/all")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<?> get(@PathVariable UUID id){
        Client item = service.get(id);
        return item != null
                ? ResponseEntity.ok(item)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping(value = "/create")
    public HttpEntity<?> create(@Valid @RequestBody ClientDto dto){
        Status create = service.create(dto);
        return ResponseEntity.status(create.getStatus()).body(create);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static Map<String, String> handleExceptions(MethodArgumentNotValidException e){
        return handleValidationExceptions(e);
    }

}

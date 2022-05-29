package com.uniteam.unishop.controller;// Created by Sardorbek Matniyazov on 5/21/2022 at 11:20 AM

import com.uniteam.unishop.domain.Client;
import com.uniteam.unishop.domain.Output;
import com.uniteam.unishop.payload.PayDto;
import com.uniteam.unishop.payload.Status;
import com.uniteam.unishop.service.serviceImpl.DebtServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.uniteam.unishop.controller.CategoryController.handleValidationExceptions;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/debt")
public class DebtController {
    private final DebtServiceImpl service;

    @GetMapping(value = "/all")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<?> get(@PathVariable Long id){
        Output item = service.get(id);
        return item != null
                ? ResponseEntity.ok(item)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping(value = "/pay")
    public HttpEntity<?> payWithId(@Valid @RequestBody PayDto dto){
        Status pay = service.pay(dto);
        return ResponseEntity.status(pay.getStatus()).body(pay);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static Map<String, String> handleExceptions(MethodArgumentNotValidException e){
        return handleValidationExceptions(e);
    }

    @GetMapping(value = "/get")
    public HttpEntity<?> getWithName(@RequestParam(value = "name") String name){
        List<Output> item = service.getByClientName(name.toLowerCase());
        return ResponseEntity.ok(item);
    }
}

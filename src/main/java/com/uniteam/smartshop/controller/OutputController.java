package com.uniteam.smartshop.controller;

import com.uniteam.smartshop.payload.OutputDto;
import com.uniteam.smartshop.payload.Status;
import com.uniteam.smartshop.service.serviceImpl.OutputServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.uniteam.smartshop.controller.CategoryController.handleValidationExceptions;

@RestController
@RequestMapping(value = "/api/v1/output")
@RequiredArgsConstructor
public class OutputController {
    private final OutputServiceImpl service;

    @GetMapping(value = "/all")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<?> get(@PathVariable Long id){
        Status item = service.get(id);
        return ResponseEntity.status(item.getStatus()).body(item);
    }

    @DeleteMapping(value = "/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        Status delete = service.delete(id);
        return ResponseEntity.status(delete.getStatus()).body(delete);
    }

    @PostMapping(value = "/create")
    public HttpEntity<?> create(@Valid @RequestBody OutputDto dto){
        Status create = service.create(dto);
        return ResponseEntity.status(create.getStatus()).body(create);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static Map<String, String> handleExceptions(MethodArgumentNotValidException e){
        return handleValidationExceptions(e);
    }
}

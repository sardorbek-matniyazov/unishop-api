package com.uniteam.unishop.controller;

import com.uniteam.unishop.domain.Client;
import com.uniteam.unishop.domain.Output;
import com.uniteam.unishop.domain.PaymentHistory;
import com.uniteam.unishop.payload.Status;
import com.uniteam.unishop.service.serviceImpl.ClientServiceImpl;
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
@RequestMapping(value = "/api/v1/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientServiceImpl service;

    @GetMapping(value = "/all")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<?> get(@PathVariable Long id){
        Client item = service.get(id);
        return item != null
                ? ResponseEntity.ok(item)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping(value = "/create")
    public HttpEntity<?> create(@Valid @RequestBody Client dto){
        Status create = service.create(dto);
        return ResponseEntity.status(create.getStatus()).body(create);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static Map<String, String> handleExceptions(MethodArgumentNotValidException e){
        return handleValidationExceptions(e);
    }

    @GetMapping(value = "/{id}/sales")
    public HttpEntity<?> getSales(@PathVariable Long id){
        List<Output> item = service.getSales(id);
        return ResponseEntity.ok(item);
    }

    @GetMapping(value = "/{id}/history")
    public HttpEntity<?> getClientPaymentHistory(@PathVariable Long id){
        List<PaymentHistory> item = service.getPaymentHistory(id);
        return ResponseEntity.ok(item);
    }

    @GetMapping(value = "/get")
    public HttpEntity<?> getWithName(@RequestParam(value = "name") String name){
        List<Client> item = service.getByName(name.toLowerCase());
        return ResponseEntity.ok(item);
    }

}

package com.uniteam.unishop.controller;

import com.uniteam.unishop.payload.OutputDto;
import com.uniteam.unishop.payload.Status;
import com.uniteam.unishop.service.serviceImpl.OutputServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Map;

import static com.uniteam.unishop.controller.CategoryController.handleValidationExceptions;

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

    @GetMapping(value = "/{id}/products")
    public HttpEntity<?> getProducts(@PathVariable Long id){
        return ResponseEntity.ok(service.getProducts(id));
    }

    @GetMapping(value = "/{id}/payments")
    public HttpEntity<?> getPayments(@PathVariable Long id){
        return ResponseEntity.ok(service.getPayments(id));
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

    @GetMapping(value = "/byDate")
    public HttpEntity<?> getOutputsByOpenAndExitDate(@RequestParam(value = "from") Long open, @RequestParam(value = "to") Long exit){
        return ResponseEntity.ok(service.getByDate(open, exit));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static Map<String, String> handleExceptions(MethodArgumentNotValidException e){
        return handleValidationExceptions(e);
    }
}

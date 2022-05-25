package com.uniteam.unishop.controller;// Created by Sardorbek Matniyazov on 5/25/2022 at 12:11 PM

import com.uniteam.unishop.service.serviceImpl.PaymentHistoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@lombok.Data
@RestController
@RequestMapping(value = "api/v1/payment")
@RequiredArgsConstructor
public class PaymentHistoryController {
    private final PaymentHistoryServiceImpl service;

    @GetMapping(value = "/sumMonths")
    public HttpEntity<?> getSums(){
        return ResponseEntity.ok(service.getSumLastSevenMonths());
    }
}

package com.uniteam.smartshop.controller;

import com.uniteam.smartshop.service.serviceImpl.OutputServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/output")
@RequiredArgsConstructor
public class OutputController {
    private final OutputServiceImpl service;
}

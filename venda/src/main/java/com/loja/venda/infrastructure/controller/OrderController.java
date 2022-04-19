package com.loja.venda.infrastructure.controller;

import com.loja.venda.domain.dto.OrderDto;
import com.loja.venda.domain.repository.OrderRepository;
import com.loja.venda.domain.repository.UserRepository;
import com.loja.venda.infrastructure.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserRepository repositoryUser;

    private final OrderService orderService;

    @PostMapping("order")
    @CircuitBreaker(name = "circuitBreakerDefault")
    public ResponseEntity save(@RequestBody OrderDto order) throws InterruptedException {
        return ResponseEntity.ok(orderService.autorizaPedido(order));
    }
}

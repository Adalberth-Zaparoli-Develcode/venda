package com.loja.venda.infrastructure.controller;

import com.loja.venda.domain.dto.OrderDto;
import com.loja.venda.domain.entity.OrderModel;
import com.loja.venda.domain.repository.OrderRepository;
import com.loja.venda.domain.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserRepository repositoryUser;

    @Autowired
    private Queue queue;

    @PostMapping("order")
    @CircuitBreaker(name = "")
    private ResponseEntity save(@NotNull @RequestBody OrderDto order){
        System.out.println("passou"+ LocalDateTime.now());
        var user = repositoryUser.findByName(order.getUser()).get();
        var model = new OrderModel();
        model.setUser(user);
        model.setProducts(order.getProducts());
        repository.save(model);
        queue.send(model);
        return ResponseEntity.ok(order);
    }
}

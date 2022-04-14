package com.loja.autentica.domain.entity;

import com.loja.autentica.domain.dto.OrderDto;
import com.loja.autentica.infrastructure.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
public class OrderController {


    @Autowired
    private OrderService queue;

    @PostMapping("order")
    private ResponseEntity save(@NotNull @RequestBody OrderDto order){
        queue.verify(order);

        return ResponseEntity.ok(order);
    }
}

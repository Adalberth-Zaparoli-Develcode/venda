package com.loja.venda.infrastructure.controller;

import com.loja.venda.domain.entity.UserModel;
import com.loja.venda.domain.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("user")
    @CircuitBreaker(name = "circuitBreakerDefault")
    private ResponseEntity save(@NotNull @RequestBody UserModel user){
        var userModel = repository.findByName(user.getName());
        if(userModel.isPresent()){
            return ResponseEntity.ok("Usuario já cadastrado");
        }else{
            repository.save(user);
            return ResponseEntity.ok(user);
        }
    }

    @PutMapping("user/add-credit")
    @CircuitBreaker(name = "circuitBreakerDefault")
    private ResponseEntity credit(@RequestBody UserModel user){
        var userModel = repository.findByName(user.getName());
        if(userModel.isPresent()){
            userModel.get().setCredit(userModel.get().getCredit().add(user.getCredit()));
            repository.save(userModel.get());
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.ok("Usuario não encontrado");
        }
    }
}

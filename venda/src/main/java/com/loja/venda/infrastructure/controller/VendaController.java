package com.loja.venda.infrastructure.controller;

import com.loja.venda.infrastructure.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping("venda/{id}")
    public ResponseEntity<Object> findVenda(@PathVariable Long id){
        var venda = vendaService.findVenda(id);
        return ResponseEntity.ok(venda);
    }
}

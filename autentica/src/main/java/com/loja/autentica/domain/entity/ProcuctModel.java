package com.loja.autentica.domain.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProcuctModel {

    private String nameProduct;

    private BigDecimal price;

    private int quantity;
}

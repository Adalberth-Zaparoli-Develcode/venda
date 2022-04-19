package com.loja.autentica.domain.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductModel {

    private String nameProduct;

    private BigDecimal price;

    private int quantity;
}

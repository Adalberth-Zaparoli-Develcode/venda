package com.loja.autentica.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
public class UserModel {

    @Id
    private String id;

    private String name;

    private BigDecimal credit;
}


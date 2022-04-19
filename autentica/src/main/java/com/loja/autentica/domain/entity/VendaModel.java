package com.loja.autentica.domain.entity;

import com.loja.autentica.domain.type.StatusType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class VendaModel {

    private Long id;

    private BigDecimal subtotal;

    private BigDecimal total;

    private String user;

    private int totalizador;

    private StatusType status;
}

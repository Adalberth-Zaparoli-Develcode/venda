package com.loja.venda.domain.entity;

import com.loja.venda.domain.type.StatusType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document
public class VendaModel {

    @Id
    private String hash;

    private Long id;

    private BigDecimal subtotal;

    private BigDecimal total;

    private String user;

    private int totalizador;

    private StatusType status;
}

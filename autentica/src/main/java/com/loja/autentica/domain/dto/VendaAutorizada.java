package com.loja.autentica.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class VendaAutorizada {
    private Long id;

    private BigDecimal amount;

    private String user;

}

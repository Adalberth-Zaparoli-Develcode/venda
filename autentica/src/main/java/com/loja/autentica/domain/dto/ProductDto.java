package com.loja.autentica.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {

    @NotNull(message = "O campo product não pode ser nulo")
    private String nameProduct;

    @NotNull(message = "O campo product não pode ser nulo")
    private BigDecimal price;

    @NotNull(message = "O campo product não pode ser nulo")
    private int quantity;
}

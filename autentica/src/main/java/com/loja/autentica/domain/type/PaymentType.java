package com.loja.autentica.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum PaymentType {

    DINHEIRO(BigDecimal.valueOf(.10)),
    DEBITO(BigDecimal.valueOf(.04)),
    CREDITO(BigDecimal.valueOf(.02)),
    PIX(BigDecimal.valueOf(.05)),
    CARTEIRA(BigDecimal.valueOf(0.03));;

    BigDecimal discount;
}

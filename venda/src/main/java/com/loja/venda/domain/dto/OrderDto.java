package com.loja.venda.domain.dto;

import com.loja.venda.domain.entity.ProcuctModel;
import com.loja.venda.domain.type.PaymentType;
import com.loja.venda.domain.type.StatusType;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Validated
public class OrderDto {

    private Long id;

    private List<ProcuctModel> products;

    private String user;

    private PaymentType payment;

    private StatusType status;
}

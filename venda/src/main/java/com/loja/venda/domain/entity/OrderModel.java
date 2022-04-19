package com.loja.venda.domain.entity;

import com.loja.venda.domain.type.PaymentType;
import com.loja.venda.domain.type.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class OrderModel {

    @Id
    private String hash;

    private Long id;

    private List<ProcuctModel> products;

    private String user;

    private PaymentType payment;

    private StatusType status;
}

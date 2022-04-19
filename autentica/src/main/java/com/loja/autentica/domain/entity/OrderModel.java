package com.loja.autentica.domain.entity;

import com.loja.autentica.domain.type.PaymentType;
import com.loja.autentica.domain.type.StatusType;
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

    private List<ProductModel> products;

    private String user;

    private PaymentType payment;

    private StatusType status;
}

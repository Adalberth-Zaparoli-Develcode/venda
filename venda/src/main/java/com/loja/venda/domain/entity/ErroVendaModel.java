package com.loja.venda.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class ErroVendaModel {

    @Id
    private String hash;

    private Long idVenda;

    private String description;

    private String message;
}

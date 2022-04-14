package com.loja.autentica.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class OrderModel {

    @Id
    private Long id;

    @Valid
    @NotNull(message = "O campo product não pode ser nulo")
    private List<ProcuctModel> product;

    private UserModel user;
}

package com.loja.venda.domain.dto;

import com.loja.venda.domain.entity.ProcuctModel;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Validated
public class OrderDto {

    @NotNull(message = "O campo id não pode ser nulo")
    private Long id;

    @NotNull(message = "O campo product não pode ser nulo")
    private List<ProcuctModel> products;

    @NotNull(message = "O campo user não pode ser nulo")
    private String user;
}

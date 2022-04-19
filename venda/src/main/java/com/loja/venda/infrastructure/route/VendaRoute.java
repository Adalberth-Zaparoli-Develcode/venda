package com.loja.venda.infrastructure.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loja.venda.domain.entity.VendaModel;
import com.loja.venda.infrastructure.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VendaRoute extends RouteBuilder {


    @Value("${loja.autorizado.endpoint}")
    private String venda;

    private final VendaService vendaService;

    private ObjectMapper objectMapper;

    @Override
    public void configure() throws Exception {
        var jacksonFormat = new JacksonDataFormat(objectMapper, VendaModel.class);
        from(venda)
                .log("Inicio processo de verificação")
                .unmarshal(jacksonFormat)
                    .bean(vendaService, "saveSale(${body})");
    }
}

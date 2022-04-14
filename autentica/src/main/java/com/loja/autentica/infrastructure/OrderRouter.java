package com.loja.autentica.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loja.autentica.domain.dto.OrderDto;
import com.loja.autentica.domain.entity.OrderModel;
import lombok.RequiredArgsConstructor;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderRouter extends RouteBuilder {

    @Autowired
    private OrderService service;

    @Value("${loja.venda.endpoint}")
    private String venda;

    private ObjectMapper objectMapper;


    @Override
    public void configure() throws Exception {
        var jacksonFormat = new JacksonDataFormat(objectMapper, OrderDto.class);
        from(venda)
                .log("Inicio processo de verificação")
                .unmarshal(jacksonFormat)
                .bean(service, "verify(${body})");
    }
}

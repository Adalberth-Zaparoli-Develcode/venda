package com.loja.autentica.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loja.autentica.domain.dto.OrderDto;
import com.loja.autentica.domain.entity.OrderModel;
import com.loja.autentica.util.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderRouter extends RouteBuilder {

    @Autowired
    private OrderService service;

    @Value("${loja.venda.endpoint}")
    private String venda;

    @Value("${loja.autorizado.endpoint}")
    private String producerTemplate;

    private final ErrorProcessor errorProcessor;

    private final OrderService orderService;

    private ObjectMapper objectMapper;

    @Override
    public void configure() throws Exception {
        onException(BusinessException.class)
                .handled(true)
                .process(errorProcessor);

        var jacksonFormat = new JacksonDataFormat(objectMapper, OrderModel.class);
        from(venda)
                .log("Inicio processo de verificação")
                .unmarshal(jacksonFormat)
                    .process(orderService)
                .end();
    }
}

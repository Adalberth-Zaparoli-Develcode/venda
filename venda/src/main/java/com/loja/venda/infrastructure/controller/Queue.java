package com.loja.venda.infrastructure.controller;

import com.loja.venda.domain.entity.OrderModel;
import com.loja.venda.util.ObjectConverterUtil;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Queue {

    @EndpointInject("{{loja.venda.endpoint}}")
    private ProducerTemplate producerTemplate;

    @Autowired
    public ObjectConverterUtil objectConverterUtil;

    public void send(OrderModel payload) {
        var json = objectConverterUtil.toJson(payload);
        producerTemplate.sendBody(json);
    }
}

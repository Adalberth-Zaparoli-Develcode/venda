package com.loja.venda.infrastructure.route;

import com.loja.venda.domain.dto.OrderDto;
import com.loja.venda.domain.entity.OrderModel;
import com.loja.venda.util.ObjectConverterUtil;
import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutorizaRoute {

    @EndpointInject("{{loja.venda.endpoint}}")
    private ProducerTemplate producerTemplate1;

    @EndpointInject("{{loja.venda.endpoint}}")
    private FluentProducerTemplate producerTemplate;

    @Autowired
    public ObjectConverterUtil objectConverterUtil;

    public void send(OrderModel payload) {
        var json = objectConverterUtil.toJson(payload);
        producerTemplate.withBody(json).send();
    }
}

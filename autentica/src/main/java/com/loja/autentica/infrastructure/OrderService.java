package com.loja.autentica.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loja.autentica.domain.dto.OrderDto;
import com.loja.autentica.domain.dto.ProcuctDto;
import com.loja.autentica.domain.dto.VendaAutorizada;
import com.loja.autentica.domain.repository.OrderRepository;
import com.loja.autentica.util.exception.BusinessException;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Validated
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @EndpointInject("{{loja.autorizado.endpoint}}")
    private ProducerTemplate producerTemplate;

    public void verify(OrderDto order){
        try {
            @Valid OrderDto order1;
            var venda = new VendaAutorizada();
            Objects.nonNull(order.getProducts());
            venda.setAmount(calculaPreco(order.getProducts()));
            venda.setId(order.getId());
            venda.setUser(order.getUser());
            producerTemplate.sendBody(toJson(venda));
        }catch (Exception e){
            throw BusinessException.builder()
                    .httpStatusCode(HttpStatus.NOT_FOUND)
                    .description("nao encontrado")
                    .message("")
                    .build();
        }
    }

    private BigDecimal calculaPreco(List<ProcuctDto> productDto){

            BigDecimal valueOrder = BigDecimal.ZERO;
            for (ProcuctDto procuct : productDto) {
                var value = procuct.getPrice().multiply(procuct.getQuantity());
                valueOrder = valueOrder.add(value);
            }
            return valueOrder;
    }

    @Autowired
    private ObjectMapper objectMapper;

    public String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    "Erro ao processar objeto para JSON", e.getMessage());
        }
    }
}


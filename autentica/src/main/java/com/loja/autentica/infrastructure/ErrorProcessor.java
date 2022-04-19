package com.loja.autentica.infrastructure;

import com.loja.autentica.domain.entity.ErroVendaModel;
import com.loja.autentica.domain.entity.OrderModel;
import com.loja.autentica.domain.repository.ErroRepository;
import com.loja.autentica.util.exception.BusinessException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ErrorProcessor implements Processor {

    @Autowired
    private ErroRepository erroRepository;

    @Override
    public void process(Exchange exchange) throws Exception {
        var a = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, BusinessException.class);
        var erro = new ErroVendaModel();
        erro.setIdVenda(exchange.getIn().getBody(OrderModel.class).getId());
        erro.setMessage(a.getMessage());
        erro.setDescription(a.getDescription());
        erroRepository.save(erro);
    }
}

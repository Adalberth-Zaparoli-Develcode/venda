package com.loja.autentica.infrastructure;

import com.loja.autentica.domain.entity.OrderModel;
import com.loja.autentica.domain.entity.ProductModel;
import com.loja.autentica.domain.entity.VendaModel;
import com.loja.autentica.domain.repository.OrderRepository;
import com.loja.autentica.domain.repository.UserRepository;
import com.loja.autentica.domain.type.PaymentType;
import com.loja.autentica.domain.type.StatusType;
import com.loja.autentica.util.ObjectConverterUtil;
import com.loja.autentica.util.exception.BusinessException;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Validated
public class OrderService implements Processor {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectConverterUtil convert;

    @EndpointInject("{{loja.autorizado.endpoint}}")
    private ProducerTemplate producerTemplate;

    @Override
    public void process(Exchange exchange) throws Exception {
        this.verify(exchange);
    }

    public void verify(Exchange exchange) {
        var order = exchange.getIn().getBody(OrderModel.class);
        var venda = new VendaModel();
        calculaPreco(venda, order);
        venda.setTotalizador(getTotalizador(order.getProducts()));
        venda.setId(order.getId());
        if(order.getPayment().equals(PaymentType.CARTEIRA)){
            descontarCarteira(venda, order.getUser());
        }
        venda.setUser(order.getUser());
        producerTemplate.sendBody(convert.toJson(venda));
    }

    private int getTotalizador(List<ProductModel> products) {
        int total = 0;
        for(ProductModel product : products){
            total = total + product.getQuantity();
        }
        return total;
    }

    private void calculaPreco(VendaModel vendaModel, OrderModel dto) {

        BigDecimal valueOrder = BigDecimal.ZERO;
        int i = 0;
        if(dto.getProducts().isEmpty() || Objects.isNull(dto.getProducts())){
            exceptionBuilder("Produtos nulo","Não é possivel realizar calculo sem itens");
        }
        for (ProductModel product : dto.getProducts()) {
            if (product.getQuantity() != 0) {
                var value = product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()));
                valueOrder = valueOrder.add(value);
            } else {
                i++;
            }
        }
        if (i > 0) {
            exceptionBuilder("Quantidade zero","A quantidade de itens nao pode ser zero");
        }
        var discount = valueOrder.multiply(dto.getPayment().getDiscount());
        vendaModel.setSubtotal(valueOrder);
        vendaModel.setTotal(valueOrder.subtract(discount));
        vendaModel.setStatus(StatusType.AGUARDANDO_PAGAMENTO);
    }

    private void descontarCarteira(VendaModel venda, String name) {
        var user = userRepository.findByName(name);
        if(user.isPresent()){
            var userGet = user.get();
            if (userGet.getCredit().compareTo(venda.getTotal()) > 0){
                venda.setStatus(StatusType.PAGO);
                userGet.setCredit(userGet.getCredit().subtract(venda.getTotal()));
                userRepository.save(userGet);
            }else {
                exceptionBuilder("Crédito insuficiente","Sem saldo suficente para conclusão da compra.");
            }
        }else{
            exceptionBuilder("Usuario inexistente","O usuario não existe.");
        }

    }

    private void exceptionBuilder(String description, String message){
        throw BusinessException.builder()
                .httpStatusCode(HttpStatus.NOT_FOUND)
                .description(description)
                .message(message)
                .build();
    }
}


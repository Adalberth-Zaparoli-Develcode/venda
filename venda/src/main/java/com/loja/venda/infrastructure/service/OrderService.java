package com.loja.venda.infrastructure.service;

import com.loja.venda.domain.dto.OrderDto;
import com.loja.venda.domain.entity.OrderModel;
import com.loja.venda.domain.repository.OrderRepository;
import com.loja.venda.infrastructure.route.AutorizaRoute;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final AutorizaRoute autorizaRoute;

    private final OrderRepository orderRepository;

    private final String MESSAGE = "Pedido salvo com sucesso";

    public String autorizaPedido(OrderDto order) throws InterruptedException {
        var model = new OrderModel();
        var id = orderRepository.findFirstByOrderByIdDesc().getId();
        id++;
        BeanUtils.copyProperties(order, model);
        model.setId(id);
        orderRepository.save(model);
        autorizaRoute.send(model);
        return MESSAGE;
    }
}

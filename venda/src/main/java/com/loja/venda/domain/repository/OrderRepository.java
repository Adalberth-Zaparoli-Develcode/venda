package com.loja.venda.domain.repository;

import com.loja.venda.domain.entity.OrderModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderModel, String> {
}

package com.loja.autentica.domain.repository;

import com.loja.autentica.domain.entity.OrderModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderModel, String> {
}

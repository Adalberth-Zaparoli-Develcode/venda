package com.loja.venda.domain.repository;

import com.loja.venda.domain.entity.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserModel, String> {

    Optional<UserModel> findByName(String name);
}

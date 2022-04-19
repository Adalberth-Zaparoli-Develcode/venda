package com.loja.autentica.domain.repository;

import com.loja.autentica.domain.entity.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserModel, String> {

    Optional<UserModel> findByName(String name);
}

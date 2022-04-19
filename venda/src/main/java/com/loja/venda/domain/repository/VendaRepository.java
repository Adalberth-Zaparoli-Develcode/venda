package com.loja.venda.domain.repository;

import com.loja.venda.domain.entity.VendaModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VendaRepository extends MongoRepository<VendaModel, String> {

    VendaModel findFirstByOrderByIdDesc();

    Optional<VendaModel> findById(Long id);
}

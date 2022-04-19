package com.loja.autentica.domain.repository;

import com.loja.autentica.domain.entity.ErroVendaModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErroRepository extends MongoRepository<ErroVendaModel, String> {

    ErroVendaModel findByIdVenda(Long id);
}

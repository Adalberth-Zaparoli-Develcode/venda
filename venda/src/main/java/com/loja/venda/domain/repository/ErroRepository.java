package com.loja.venda.domain.repository;

import com.loja.venda.domain.entity.ErroVendaModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErroRepository extends MongoRepository<ErroVendaModel, String> {

    ErroVendaModel findByIdVenda(Long id);
}

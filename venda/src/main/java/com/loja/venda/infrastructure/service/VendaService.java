package com.loja.venda.infrastructure.service;

import com.loja.venda.domain.entity.ErroVendaModel;
import com.loja.venda.domain.entity.VendaModel;
import com.loja.venda.domain.repository.ErroRepository;
import com.loja.venda.domain.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ErroRepository erroRepository;

    public void saveSale(VendaModel venda){
        vendaRepository.save(venda);
    }

    public Object findVenda(Long id){
        var venda = vendaRepository.findById(id);
        if(venda.isPresent()){
            return venda.get();
        }else {
            return erroRepository.findByIdVenda(id);
        }
    }
}

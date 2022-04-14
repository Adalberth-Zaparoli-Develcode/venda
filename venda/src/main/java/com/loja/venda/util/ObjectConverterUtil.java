package com.loja.venda.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class transforms java objects into json String
 *
 * @author Rodrigo Mattioda
 */
@Component
@Getter
public class ObjectConverterUtil {

    @Autowired
    private ObjectMapper objectMapper;

    public String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao transformar em json");
        }
    }
}
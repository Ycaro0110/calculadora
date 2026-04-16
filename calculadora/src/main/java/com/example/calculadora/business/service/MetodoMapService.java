package com.example.calculadora.business.service;

import com.example.calculadora.controller.DTO.ParamRequestDTO;
import com.example.calculadora.controller.DTO.ResponseDTO;
import com.example.calculadora.infrastructure.exceptions.ErroCalculo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MetodoMapService {

    private final Map<String, MetodoService> services;

    public ResponseDTO calcular(String metodo, ParamRequestDTO parametros) {

        MetodoService service = services.get(metodo.toLowerCase() + "Service");

        if (service == null) {
            throw new ErroCalculo("Metodo invalido");
        }
        return service.calcular(parametros);

    }

}

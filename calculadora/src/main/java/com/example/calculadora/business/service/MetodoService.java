package com.example.calculadora.business.service;

import com.example.calculadora.controller.DTO.ParamRequestDTO;
import com.example.calculadora.controller.DTO.ResponseDTO;

public interface MetodoService {

    ResponseDTO calcular(ParamRequestDTO parametros);
}

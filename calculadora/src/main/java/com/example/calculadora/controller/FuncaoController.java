package com.example.calculadora.controller;


import com.example.calculadora.business.service.MetodoMapService;
import com.example.calculadora.controller.DTO.ParamRequestDTO;
import com.example.calculadora.controller.DTO.ResponseDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calcular")
public class FuncaoController {

    private final MetodoMapService metodoMapService;

    @PostMapping("/{metodo}")
    public ResponseDTO bissecao(@RequestBody ParamRequestDTO parametros, @PathVariable String metodo) {

        return metodoMapService.calcular(metodo, parametros);
    }


}

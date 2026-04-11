package com.example.calculadora.controller;


import com.example.calculadora.DTO.Metodo;
import com.example.calculadora.DTO.ParamRequestDTO;
import com.example.calculadora.DTO.ResponseDTO;
import com.example.calculadora.service.BissecaoService;
import com.example.calculadora.service.NewtonService;
import com.example.calculadora.service.Ponto_falsoService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/calcular")
public class FuncaoController {

    private final BissecaoService bissecaoService;
    private final NewtonService newtonService;
    private final Ponto_falsoService ponto_falsoService;

    @PostMapping("/{metodo}")
    public ResponseDTO bissecao(@RequestBody ParamRequestDTO parametros, @PathVariable Metodo metodo) {

        return switch (metodo) {
            case bissecao -> bissecaoService.calcular(parametros);
            case newton -> newtonService.calcular(parametros);
            case ponto_falso -> ponto_falsoService.calcular(parametros);
        };
    }


}

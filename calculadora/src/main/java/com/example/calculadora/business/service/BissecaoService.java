package com.example.calculadora.business.service;

import com.example.calculadora.controller.DTO.ParamRequestDTO;
import com.example.calculadora.controller.DTO.ResponseDTO;
import com.example.calculadora.infrastructure.exceptions.ErroCalculo;
import org.mariuszgromada.math.mxparser.Function;
import org.springframework.stereotype.Service;

@Service

public class BissecaoService implements MetodoService{

    private int iteracoes = 0;
    private double erro = 0;
    private double raiz = 0;

    @Override
    public ResponseDTO calcular(ParamRequestDTO parametros) {

        double x1 = parametros.x1();
        double x2 = parametros.x2();
        double e = parametros.erro();
        String func = parametros.function();

        Function f = new Function("f(x) = " + func);

        int max_iteracao = (int) (Math.ceil(Math.log(x2 - x1) - Math.log(e)) / Math.log(2));

        double x_medio = 0;
        double x_old;
        double erro_estimado = 0;
        int num_iteracao = 0;

        iteracoes = 0;

        do {
            double bolzano = f.calculate(x1) * f.calculate(x2);
            if (bolzano > 0) {
                throw new ErroCalculo("Não existe raiz neste intervalo");
            }
            x_old = x_medio;

            x_medio = (x1 + x2) / 2;
            num_iteracao++;
            this.iteracoes++;

            if (f.calculate(x_medio) == 0) {
                return new ResponseDTO(iteracoes, erro, raiz);
            }
            if (f.calculate(x1) == 0) {
                return new ResponseDTO(iteracoes, erro, raiz);
            } else if (f.calculate(x2) == 0) {
                return new ResponseDTO(iteracoes, erro, raiz);
            }
            double teste = f.calculate(x1) * f.calculate(x_medio);
            if (teste > 0) {
                x1 = x_medio;
            } else {
                x2 = x_medio;
            }
            erro_estimado = Math.abs(x_medio - x_old);

        } while (erro_estimado >= e || num_iteracao < max_iteracao);

        this.erro = erro_estimado;

        raiz = x_medio;

        return new ResponseDTO(iteracoes, erro, raiz);
    }
}

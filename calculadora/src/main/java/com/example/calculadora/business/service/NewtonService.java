package com.example.calculadora.business.service;

import com.example.calculadora.controller.DTO.ParamRequestDTO;
import com.example.calculadora.controller.DTO.ResponseDTO;
import com.example.calculadora.infrastructure.exceptions.ErroCalculo;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.stereotype.Service;


@Service
public class NewtonService implements MetodoService {

    private double erro = 0;
    private int iteracoes = 0;
    private double raiz = 0;

    @Override
    public ResponseDTO calcular(ParamRequestDTO parametros) {




        double x0 = parametros.x1();
        double erroTolerado = parametros.erro();
        String funcStr = parametros.function();

        iteracoes = 0;

        Argument x = new Argument("x = " + x0);

        Expression func = new Expression(funcStr, x);

        Expression derivFunc = new Expression("der(" + funcStr + ", x)", x);

        double fx, dfx, x1, erroAtual;


        do {
            fx = func.calculate();
            dfx = derivFunc.calculate();

            if (dfx == 0) {
                throw new ErroCalculo("Derivada é zero, o método falhou.");
            }
            x1 = x0 - fx / dfx;

            erroAtual = Math.abs(x1 - x0);
            x.setArgumentValue(x1);
            x0 = x1;
            this.iteracoes++;


        } while (erroAtual > erroTolerado);

        this.erro = erroAtual;

        raiz = x1;

        return new ResponseDTO(iteracoes, erro, raiz);

    }
}

package com.example.calculadora.service;

import com.example.calculadora.DTO.ParamRequestDTO;
import com.example.calculadora.DTO.ResponseDTO;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.stereotype.Service;


@Service
public class NewtonService {

    private double erro = 0;
    private int iteracoes = 0;
    private double raiz = 0;


    public ResponseDTO calcular(ParamRequestDTO parametros) {

        double x0 = parametros.x1();
        double x2 = parametros.x2();
        double erroTolerado = parametros.erro();
        String funcStr = parametros.function();

        iteracoes = 0;

        Argument x = new Argument("x = " + x0);

        Expression func = new Expression(funcStr, x);

        Expression derivFunc = new Expression("der(" + funcStr + ", x)", x);

        double fx, dfx, x1 = 0, erroAtual = 0;


        do {
            fx = func.calculate();
            dfx = derivFunc.calculate();

            if (dfx == 0) {
                System.out.println("Derivada é zero, o método falhou.");
                break;
            }
            x1 = x0 - fx / dfx;

            erroAtual = Math.abs(x1 - x0);
            x.setArgumentValue(x1);
            x0 = x1;
            this.iteracoes++;
            System.out.println("Iteração: x = " + x1 + ", Erro atual = " + erroAtual);


        } while (erroAtual > erroTolerado);

        this.erro = erroAtual;

        raiz = x1;

        return new ResponseDTO(iteracoes, erro, raiz);

    }
}

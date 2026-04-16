package com.example.calculadora.business.service;

import com.example.calculadora.controller.DTO.ParamRequestDTO;
import com.example.calculadora.controller.DTO.ResponseDTO;
import com.example.calculadora.infrastructure.exceptions.ErroCalculo;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.stereotype.Service;

@Service
public class PontoFalsoService implements MetodoService {

    private double erro;
    private int iteracoes = 0;
    private double raiz = 0;

    @Override
    public ResponseDTO calcular(ParamRequestDTO parametros) {

        double a = parametros.x1();
        double b = parametros.x2();
        double erroTolerado = parametros.erro();
        String funcStr = parametros.function();

        iteracoes = 0;

        Argument x = new Argument("x");
        Expression func = new Expression(funcStr, x);

        x.setArgumentValue(a);
        double fa = func.calculate();
        x.setArgumentValue(b);
        double fb = func.calculate();

        if (fa * fb >= 0) {

            throw new ErroCalculo("f(a) e f(b) devem ter sinais opostos.");
        }
        double xr;
        double erroAtual;

        do {
            x.setArgumentValue(a);
            fa = func.calculate();
            x.setArgumentValue(b);
            fb = func.calculate();
            xr = (a * fb - b * fa) / (fb - fa);
            x.setArgumentValue(xr);
            double fr = func.calculate();

            if (Math.abs(fr) < erroTolerado) {
                return new ResponseDTO(iteracoes, erro, raiz);

            }
            if (fa * fr < 0) {
                b = xr;
            } else {
                a = xr;
            }

            erroAtual = Math.abs(b - a);
            this.iteracoes++;
            this.erro = erroAtual;
            this.raiz = xr;
        } while (erroAtual > erroTolerado);

        return new ResponseDTO(iteracoes, erro, raiz);

    }
}

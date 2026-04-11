package com.example.calculadora.service;

import com.example.calculadora.DTO.ParamRequestDTO;
import com.example.calculadora.DTO.ResponseDTO;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.stereotype.Service;

@Service
public class Ponto_falsoService {

    private double erro;
    private int iteracoes = 0;
    private double raiz = 0;

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
            System.out.println("f(a) e f(b) devem ter sinais opostos.");
            return new ResponseDTO(iteracoes, erro, raiz);
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
                System.out.println("Raiz encontrada: x = " + xr);
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

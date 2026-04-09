package com.example.calculadora.service;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.stereotype.Service;


@Service
public class NewtonService {

    public double erro = 0;
    public int iteracoes = 0;
    public double raiz = 0;


    public void calcular(String funcStr, double x0, double erroTolerado) {

        // Inicializando a variável x0
        Argument x = new Argument("x = " + x0);

        // Expressão para a função
        Expression func = new Expression(funcStr, x);

        // Expressão para a derivada da função
        Expression derivFunc = new Expression("der(" + funcStr + ", x)", x);

        double fx, dfx, x1, erroAtual;

        // Realiza a primeira iteração
        do {
            // Calcula o valor da função e da derivada no ponto atual
            fx = func.calculate();
            dfx = derivFunc.calculate();

            // Se a derivada for zero, o método falha e não pode calcular
            if (dfx == 0) {
                System.out.println("Derivada é zero, o método falhou.");
                return;
            }

            // Calcula o próximo valor de x usando a fórmula de Newton-Raphson
            x1 = x0 - fx / dfx;

            // Calcula o erro atual (diferença entre o valor anterior e o novo)
            erroAtual = Math.abs(x1 - x0);

            // Atualiza o valor de x para fazer próxima iteração
            x.setArgumentValue(x1);
            x0 = x1;

            // Mostra o erro atual para acompanhar as iterações
            this.iteracoes++;
            System.out.println("Iteração: x = " + x1 + ", Erro atual = " + erroAtual);

        } while (erroAtual > erroTolerado);

        // Retorna o valor aproximado da raiz
        this.erro = erroAtual;


        raiz = x1;
    }


}

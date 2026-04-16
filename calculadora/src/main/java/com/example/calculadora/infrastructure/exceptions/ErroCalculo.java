package com.example.calculadora.infrastructure.exceptions;

public class ErroCalculo extends RuntimeException {
    public ErroCalculo(String message) {
        super(message);
    }
}

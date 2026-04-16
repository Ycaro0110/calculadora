package com.example.calculadora.controller.DTO;


import com.example.calculadora.infrastructure.exceptions.ErroCalculo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler(ErroCalculo.class)
    public ResponseEntity<String> handleGeneric(ErroCalculo e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

}

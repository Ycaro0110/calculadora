package com.example.calculadora.controller;


import com.example.calculadora.model.ParamRequestDTO;
import com.example.calculadora.service.BissecaoService;
import com.example.calculadora.service.NewtonService;
import com.example.calculadora.service.Ponto_falsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/calcular")

public class FuncaoController {


    BissecaoService bissecaoService;
    NewtonService newtonService;
    Ponto_falsoService ponto_falsoService;

    @Autowired
    public FuncaoController(BissecaoService bissecaoService, NewtonService newtonService, Ponto_falsoService pontoFalso) {
        this.bissecaoService = bissecaoService;
        this.newtonService = newtonService;
        this.ponto_falsoService = pontoFalso;
    }

    @PostMapping("/bissecao")
    public ResponseEntity<BissecaoService> bissecao(@RequestBody ParamRequestDTO parametros) {

        BissecaoService bissecaoService = new BissecaoService();
        bissecaoService.calcular(parametros.x1(), parametros.x2(), parametros.erro(), parametros.function());
        return ResponseEntity.ok(bissecaoService);

    }

    @PostMapping("/newton")
    public ResponseEntity<NewtonService> newton(@RequestBody ParamRequestDTO parametros) {
        NewtonService newtonService = new NewtonService();
        newtonService.calcular(parametros.function(), parametros.x1(), parametros.erro());
        return ResponseEntity.ok(newtonService);
    }

    @PostMapping("/ponto_falso")
    public ResponseEntity<Ponto_falsoService> calcular(@RequestBody ParamRequestDTO parametros) {
        Ponto_falsoService ponto_falsoService = new Ponto_falsoService();
        ponto_falsoService.calcular(parametros.function(), parametros.x1(), parametros.x2(), parametros.erro());
        return ResponseEntity.ok(ponto_falsoService);
    }


}

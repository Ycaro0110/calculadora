package com.example.calculadora.controller;


import com.example.calculadora.model.ParamRequestDTO;
import com.example.calculadora.model.ResponseDTO;
import com.example.calculadora.service.BissecaoService;
import com.example.calculadora.service.NewtonService;
import com.example.calculadora.service.Ponto_falsoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/calcular")

public class FuncaoController {


    BissecaoService bissecaoService;
    NewtonService newtonService;
    Ponto_falsoService ponto_falsoService;

//    @Autowired
//    public FuncaoController(BissecaoService bissecaoService, NewtonService newtonService, Ponto_falsoService pontoFalso) {
//        this.bissecaoService = bissecaoService;
//        this.newtonService = newtonService;
//        this.ponto_falsoService = pontoFalso;
//    }

    @PostMapping("/bissecao")
    public ResponseEntity<ResponseDTO> bissecao(@RequestBody ParamRequestDTO parametros) {

        bissecaoService = new BissecaoService();
        bissecaoService.calcular(parametros.x1(), parametros.x2(), parametros.erro(), parametros.function());
        return ResponseEntity.ok(new ResponseDTO(bissecaoService.getIteracoes(), bissecaoService.getErro(), bissecaoService.getRaiz()));

    }

    @PostMapping("/newton")
    public ResponseEntity<ResponseDTO> newton(@RequestBody ParamRequestDTO parametros) {
        newtonService = new NewtonService();
        newtonService.calcular(parametros.function(), parametros.x1(), parametros.erro());
        return ResponseEntity.ok(new ResponseDTO(newtonService.getIteracoes(), newtonService.getErro(), newtonService.getRaiz()));
    }

    @PostMapping("/ponto_falso")
    public ResponseEntity<ResponseDTO> calcular(@RequestBody ParamRequestDTO parametros) {
        ponto_falsoService = new Ponto_falsoService();
        ponto_falsoService.calcular(parametros.function(), parametros.x1(), parametros.x2(), parametros.erro());
        return ResponseEntity.ok(new ResponseDTO(ponto_falsoService.getIteracoes(), ponto_falsoService.getErro(), ponto_falsoService.getRaiz()));
    }


}

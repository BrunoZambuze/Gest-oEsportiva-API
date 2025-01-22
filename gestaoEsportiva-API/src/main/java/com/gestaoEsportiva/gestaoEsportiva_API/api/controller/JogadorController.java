package com.gestaoEsportiva.gestaoEsportiva_API.api.controller;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Jogador;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Jogador salvarJogador(@RequestBody Jogador jogador){
        return jogadorService.salvar(jogador);
    }

    @DeleteMapping("/{jogadorId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletar(@PathVariable Long jogadorId){
        jogadorService.deletar(jogadorId);
    }

}

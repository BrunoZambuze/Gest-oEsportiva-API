package com.gestaoEsportiva.gestaoEsportiva_API.api.controller;

import com.gestaoEsportiva.gestaoEsportiva_API.api.core.validation.Groups;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Jogador;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.repository.JogadorRepository;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.groups.Default;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @Autowired
    private JogadorRepository jogadorRepository;

    @GetMapping("{jogadorId}")
    public Jogador buscar(@PathVariable Long jogadorId){
        return jogadorRepository.findByIdOrElseThrowExpcetion(jogadorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Jogador salvarJogador(@Validated({Default.class, Groups.CadastroTime.class}) @RequestBody Jogador jogador){
        return jogadorService.salvar(jogador);
    }

    @DeleteMapping("/{jogadorId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletar(@PathVariable Long jogadorId){

        jogadorService.deletar(jogadorId);

    }

    @PutMapping("{jogadorId}")
    @ResponseStatus(HttpStatus.OK)
    public Jogador atualizar(@PathVariable Long jogadorId,
                             @Valid @RequestBody Jogador jogadorAlterar){
        return jogadorService.atualizar(jogadorId, jogadorAlterar);
    }

}

package com.gestaoEsportiva.gestaoEsportiva_API.api.controller;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Jogador;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.exception.JogadorNaoEncontradoExpcetion;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.exception.TimeNaoEncontradoException;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.repository.JogadorRepository;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @Autowired
    private JogadorRepository jogadorRepository;

    @GetMapping("{jogadorId}")
    public ResponseEntity<?> buscar(@PathVariable Long jogadorId){
        try
        {
            Jogador jogadorEncontrado = jogadorRepository.findByIdOrElseThrowExpcetion(jogadorId);
            return ResponseEntity.status(HttpStatus.OK).body(jogadorEncontrado);
        } catch (JogadorNaoEncontradoExpcetion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvarJogador(@RequestBody Jogador jogador){
        try{
            Jogador jogadorEncontrado = jogadorService.salvar(jogador);
            return ResponseEntity.status(HttpStatus.CREATED).body(jogadorEncontrado);
        }catch (TimeNaoEncontradoException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{jogadorId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deletar(@PathVariable Long jogadorId){
        try {
            jogadorService.deletar(jogadorId);
            return ResponseEntity.noContent().build();
        }catch (JogadorNaoEncontradoExpcetion e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("{jogadorId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> atualizar(@PathVariable Long jogadorId,
                        @RequestBody Jogador jogadorAlterar){
        try{
            Jogador jogadorAtualizado = jogadorService.atualizar(jogadorId, jogadorAlterar);
            return ResponseEntity.ok().body(jogadorAtualizado);
        }catch (JogadorNaoEncontradoExpcetion e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (TimeNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

package com.gestaoEsportiva.gestaoEsportiva_API.api.controller;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Jogador;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Time;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.exception.EntidadeEmUsoException;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.exception.TimeNaoEncontradoException;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.repository.TimeRepository;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private TimeService timeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Time adicionarTime(@RequestBody Time time){
        return timeService.salvar(time);
    }

    @GetMapping("/{timeId}")
    public ResponseEntity<?> buscarTime(@PathVariable Long timeId){
        try{
            Time timeEncontrado = timeRepository.findByIdOrElseThrowException(timeId);
            return ResponseEntity.ok(timeEncontrado);
        }catch (TimeNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/jogadores/{timeId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> visualizarJogadores(@PathVariable Long timeId){
        try{
            List<Jogador> listaJogadores = timeService.buscarJogadores(timeId);
            return ResponseEntity.ok(listaJogadores);
        }catch (TimeNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{timeId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> atualizar(@RequestBody Time timeAtualizar,
                                          @PathVariable Long timeId){
        try{
            Time timeAtualizado = timeService.atualizar(timeAtualizar, timeId);
            return ResponseEntity.ok(timeAtualizado);
        }catch (TimeNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("{timeId}")
    public ResponseEntity<?> deletar(@PathVariable Long timeId){
        try{
            timeService.deletar(timeId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (TimeNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}

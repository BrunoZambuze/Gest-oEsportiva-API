package com.gestaoEsportiva.gestaoEsportiva_API.api.controller;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Jogador;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Time;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.repository.TimeRepository;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private TimeService timeService;

    private final static HttpStatus status = HttpStatus.BAD_REQUEST;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Time adicionarTime(@RequestBody Time time){
        return timeService.salvar(time);
    }

    @GetMapping("/{timeId}")
    public Time buscarTime(@PathVariable Long timeId){
            return timeRepository.findByIdOrElseThrowException(timeId);
    }

    @GetMapping("/jogadores/{timeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Jogador> visualizarJogadores(@PathVariable Long timeId){
            return timeService.buscarJogadores(timeId);
    }

    @PutMapping("/{timeId}")
    @ResponseStatus(HttpStatus.OK)
    public Time atualizar(@RequestBody Time timeAtualizar,
                                          @PathVariable Long timeId){
            return timeService.atualizar(timeAtualizar, timeId);
    }

    @DeleteMapping("{timeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long timeId){
            timeService.deletar(timeId);
    }

}

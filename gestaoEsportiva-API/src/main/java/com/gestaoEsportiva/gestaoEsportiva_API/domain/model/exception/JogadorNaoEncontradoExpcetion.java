package com.gestaoEsportiva.gestaoEsportiva_API.domain.model.exception;

public class JogadorNaoEncontradoExpcetion extends EntidadeNaoEncontradaException{

    public JogadorNaoEncontradoExpcetion(String message) {
        super(message);
    }

    public JogadorNaoEncontradoExpcetion(Long jogadorId){
        this(String.format("Não foi possível encontrar um jogador com id '%s'", jogadorId));
    }
}

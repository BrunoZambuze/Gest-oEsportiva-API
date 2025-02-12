package com.gestaoEsportiva.gestaoEsportiva_API.domain.model.exception;

public class TimeNaoEncontradoException extends EntidadeNaoEncontradaException {

    public TimeNaoEncontradoException(String message) {
        super(message);
    }

    public TimeNaoEncontradoException(Long id){
        this(String.format("Não foi possível encontrar um time com id '%s'", id));
    }
}
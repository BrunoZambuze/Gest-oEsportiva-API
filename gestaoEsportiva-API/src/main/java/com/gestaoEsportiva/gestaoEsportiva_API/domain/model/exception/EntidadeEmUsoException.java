package com.gestaoEsportiva.gestaoEsportiva_API.domain.model.exception;

public class EntidadeEmUsoException extends RuntimeException {

    public EntidadeEmUsoException(String message) {
        super(message);
    }

    public EntidadeEmUsoException(){
        this("O valor não pode ser removido pois está sendo utilizado por outra entidade");
    }
}

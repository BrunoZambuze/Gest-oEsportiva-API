package com.gestaoEsportiva.gestaoEsportiva_API.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Field {

    private String nome;
    private String uiMessage;
    private Object valorRejeitado;

}

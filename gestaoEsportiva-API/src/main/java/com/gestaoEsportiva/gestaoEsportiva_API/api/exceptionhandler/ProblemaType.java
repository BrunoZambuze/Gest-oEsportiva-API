package com.gestaoEsportiva.gestaoEsportiva_API.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemaType {

    URI_INVALIDA("Uri inválida", "/uri-invalida"),
    MENSAGEM_INCOMPREESIVEL("Mensagel incompresível", "/mensagem-incompreensivel"),
    TIPO_DE_MIDIA_NAO_SUPORTADO("Tipo de mídia não suportado", "tipo-de-midia-nao-suportado"),
    ENTIDADE_NAO_ENCONTRADA("Entiade não encontrada", "/entidade-nao-encontrada"),
    ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso");

    private String title;
    private String uri;

    ProblemaType(String title, String path){
        this.title = title;
        this.uri = "https.gestaoesportiva.com.br" + path;
    }

}

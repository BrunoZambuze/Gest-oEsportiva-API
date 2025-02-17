package com.gestaoEsportiva.gestaoEsportiva_API.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problema {

    private int status;
    private String type;
    private String title;
    private String detail;

    private List<Field> fields = new ArrayList<>();

}

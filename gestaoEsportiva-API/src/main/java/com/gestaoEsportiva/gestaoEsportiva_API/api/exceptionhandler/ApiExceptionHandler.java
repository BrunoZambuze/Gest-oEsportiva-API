package com.gestaoEsportiva.gestaoEsportiva_API.api.exceptionhandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.exception.EntidadeEmUsoException;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.exception.EntidadeNaoEncontradaException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> handleJsonParseException(JsonParseException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest){

        String detail = String.format("A propriedade %s possui a sintaxe errada, pois enviou o valor '%s' que não é aceito!. " +
                                       "Por favor corrija!", "terminar", "depois");
        ProblemaType problemaType = ProblemaType.MENSAGEM_INCOMPREESIVEL;

        Problema problema = createProblemaBuilder(status, problemaType, detail).build();

        return handleExceptionInternal(ex, problema, headers, status, webRequest);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest){

        String path = ex.getPath()
                .stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        ProblemaType problemaType = ProblemaType.MENSAGEM_INCOMPREESIVEL;
        String detail = String.format("A propriedade %s recebeu o valor '%s', que é de um tipo inválido. Corrija e informe " +
                "um valor compatível com o tipo '%s'", path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problema problema = createProblemaBuilder(status, problemaType, detail).build();

        return handleExceptionInternal(ex, problema, headers, status, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if(rootCause instanceof InvalidFormatException){
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        } else if(rootCause instanceof JsonParseException){
            return handleJsonParseException((JsonParseException) rootCause, headers, status, request);
        }

        ProblemaType problemaType = ProblemaType.MENSAGEM_INCOMPREESIVEL;
        String detail = "O corpo da requisição está inválido! Por favor verifique a sintaxe";

        Problema problema = createProblemaBuilder(status, problemaType, detail).build();

        return this.handleExceptionInternal(ex, problema, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemaType problemaType = ProblemaType.TIPO_DE_MIDIA_NAO_SUPORTADO;
        String detail = "Tipo de mídia não suportado. Utilize um formato compatível";

        Problema problema = createProblemaBuilder(status, problemaType, detail).build();

        return this.handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException e, WebRequest webRequest){

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemaType problemaType = ProblemaType.ENTIDADE_NAO_ENCONTRADA;
        String detail = e.getMessage();

        Problema problema = createProblemaBuilder(status, problemaType, detail).build();

        return this.handleExceptionInternal(e, problema, new HttpHeaders(), status, webRequest);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException e, WebRequest webRequest){

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemaType problemaType = ProblemaType.ENTIDADE_EM_USO;
        String detail = e.getMessage();

        Problema problema = createProblemaBuilder(status, problemaType, detail).build();

        return this.handleExceptionInternal(e, problema, new HttpHeaders(), status, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if(body == null){
            body = Problema.builder()
                    .status(status.value())
                    .title(status.getReasonPhrase())
                    .build();
        } else if(body instanceof String){
            body = Problema.builder()
                    .status(status.value())
                    .title((String) body)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problema.ProblemaBuilder createProblemaBuilder(HttpStatus status, ProblemaType problemaType, String detail){
        return Problema.builder()
                .status(status.value())
                .type(problemaType.getUri())
                .title(problemaType.getTitle())
                .detail(detail);
    }
}

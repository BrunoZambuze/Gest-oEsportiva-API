package com.gestaoEsportiva.gestaoEsportiva_API.api.exceptionhandler;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.exception.EntidadeEmUsoException;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.exception.EntidadeNaoEncontradaException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> handleUnrecognizedProperty(UnrecognizedPropertyException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest){

        ProblemaType problemaType = ProblemaType.MENSAGEM_INCOMPREESIVEL;
        String detail = String.format("A propriedade '%s' não existe no contexto da aplicação! Por favor corrija e " +
                "tente novamente", ex.getPropertyName());

        Problema problema = createProblemaBuilder(status, problemaType, detail).build();

        return handleExceptionInternal(ex, problema, headers, status, webRequest);
    }

    private ResponseEntity<Object> handleJsonParse(JsonParseException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest){

        JsonLocation location = ex.getLocation();

        String detail = String.format("Erro de sintaxe no JSON: A linha %d e coluna %d foram escritas de maneira errada! " +
                "Jackson não entendeu a estrutura do JSON", location.getLineNr(), location.getColumnNr());
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
            return handleJsonParse((JsonParseException) rootCause, headers, status, request);
        } else if(rootCause instanceof UnrecognizedPropertyException){
            return handleUnrecognizedProperty((UnrecognizedPropertyException) rootCause, headers, status, request);
        }

        ProblemaType problemaType = ProblemaType.MENSAGEM_INCOMPREESIVEL;
        String detail = "O corpo da requisição está inválido! Por favor verifique a sintaxe";

        Problema problema = createProblemaBuilder(status, problemaType, detail).build();

        return this.handleExceptionInternal(ex, problema, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemaType problemaType = ProblemaType.DADOS_INVALIDOS;
        String detail = "Um ou mais campos estão preenchidos de maneira errada. Por favor corrija-os";

        BindingResult bindingResult = ex.getBindingResult();
        List<Field> problemaFields = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> Field.builder()
                        .nome(fieldError.getField())
                        .uiMessage(fieldError.getDefaultMessage())
                        .valorRejeitado(fieldError.getRejectedValue())
                        .build())
                .collect(Collectors.toList());

        Problema problema = createProblemaBuilder(status, problemaType, detail).fields(problemaFields).build();

        return this.handleExceptionInternal(ex, problema, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemaType problemaType = ProblemaType.URI_INVALIDA;
        String detail = String.format("A URI informada '%s' não existe! Por favavor corrija e tente novamente", ex.getRequestURL());

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

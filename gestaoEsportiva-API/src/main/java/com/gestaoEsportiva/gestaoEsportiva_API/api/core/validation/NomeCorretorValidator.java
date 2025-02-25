package com.gestaoEsportiva.gestaoEsportiva_API.api.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NomeCorretorValidator implements ConstraintValidator<NomeCorreto, String> {

    @Override
    public boolean isValid(String nome, ConstraintValidatorContext constraintValidatorContext) {

        boolean valido = true;
        if(nome == null){
            return false;
        }
        return nome.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$");
    }
}

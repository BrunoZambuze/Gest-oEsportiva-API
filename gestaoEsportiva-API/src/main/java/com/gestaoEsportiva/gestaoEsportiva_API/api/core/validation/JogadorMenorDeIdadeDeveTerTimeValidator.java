package com.gestaoEsportiva.gestaoEsportiva_API.api.core.validation;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Jogador;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class JogadorMenorDeIdadeDeveTerTimeValidator implements ConstraintValidator<JogadorMenorDeIdadeDeveTerTime, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {

        boolean valido = true;

        if(obj == null || !(obj instanceof Jogador)){
            return false;
        }

        Jogador jogador = (Jogador) obj;

        if(jogador.getDataNascimento() == null){
            return false;
        }

        int idade = Period.between(jogador.getDataNascimento(), LocalDate.now()).getYears();

        if(idade < 18 && jogador.getTime() == null || jogador.getTime().getId() == null){
            return false;
        }

        return valido;
    }

}

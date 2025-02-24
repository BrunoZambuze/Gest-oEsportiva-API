package com.gestaoEsportiva.gestaoEsportiva_API.api.core.validation;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.PastOrPresent;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@PastOrPresent
public @interface DataValida {

    @OverridesAttribute(constraint = PastOrPresent.class, name = "message")
    String message() default "{DataValida.invalida}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

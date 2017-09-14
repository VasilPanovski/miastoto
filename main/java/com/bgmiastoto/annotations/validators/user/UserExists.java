package com.bgmiastoto.annotations.validators.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UserExistsValidator.class)
public @interface UserExists {

    String message() default "Потребителското име е заето. Избери друго.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

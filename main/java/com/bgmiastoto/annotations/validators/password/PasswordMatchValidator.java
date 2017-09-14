package com.bgmiastoto.annotations.validators.password;

import com.bgmiastoto.models.bindingModels.UserRegistrationModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {
    @Override
    public void initialize(PasswordMatch isPasswordsMatching) {

    }

    @Override
    public boolean isValid(Object userClass, ConstraintValidatorContext constraintValidatorContext) {
        if(userClass instanceof UserRegistrationModel){
            return ((UserRegistrationModel) userClass).getPassword().equals(((UserRegistrationModel) userClass).getConfirmPassword());
        }

        return false;
    }
}

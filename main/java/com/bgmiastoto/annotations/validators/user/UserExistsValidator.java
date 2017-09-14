package com.bgmiastoto.annotations.validators.user;

import com.bgmiastoto.entities.users.User;
import com.bgmiastoto.models.bindingModels.UserRegistrationModel;
import com.bgmiastoto.services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserExistsValidator implements ConstraintValidator<UserExists, String> {

    private final ApplicationUserService applicationUserService;

    @Autowired
    public UserExistsValidator(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @Override
    public void initialize(UserExists userExists) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        User applicationUser = this.applicationUserService.findByUsername(username);
        return applicationUser == null;
    }
}

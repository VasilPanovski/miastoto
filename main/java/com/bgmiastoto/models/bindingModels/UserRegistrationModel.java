package com.bgmiastoto.models.bindingModels;

import com.bgmiastoto.annotations.validators.password.PasswordMatch;
import com.bgmiastoto.annotations.validators.user.UserExists;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@PasswordMatch
public class UserRegistrationModel {

    @UserExists
    @Size(min = 5, message = "Потребителското име трябва да съдържа минимум 5 символа.")
    private String name;

    @Email
    @Size(min = 7, message = "Имейлът не може да бъде по-кратък от 7 символа")
    @Pattern(regexp = "^[a-zA-Z0-9_\\-+%]+@[A-Za-z.]{2,10}.[a-z]{2,4}$", message = "Невалиден имейл адрес")
    private String email;

    @Size(min = 6, message = "Паролата трябва да съдържа поне 6 символа")
    private String password;

    private String confirmPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

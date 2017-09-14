package com.bgmiastoto.services;

import com.bgmiastoto.entities.users.ApplicationUser;
import com.bgmiastoto.entities.users.User;
import com.bgmiastoto.models.bindingModels.UserRegistrationModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ApplicationUserService extends UserDetailsService{

    void registerUser(UserRegistrationModel userRegistrationModel);

    User findByUsername(String username);

}

package com.bgmiastoto.services;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.facebook.api.User;

public interface SocialUserService extends UserDetailsService {

    void registerOrLogin(User facebookUser);
}

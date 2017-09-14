package com.bgmiastoto.controllers;

import com.bgmiastoto.services.SocialUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PostData;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users/register")
public class FacebookLoginController {

    @Autowired
    private SocialUserService socialUserService;

    private Facebook facebook;

    private ConnectionRepository connectionRepository;

    public FacebookLoginController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping("facebook")
    public String registerOrLogin() {

        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        ConnectionKey connectionKey = this.connectionRepository.findPrimaryConnection(Facebook.class).getKey();
        String userKey = this.connectionRepository.findPrimaryConnection(Facebook.class).getKey().getProviderUserId();
        //this.facebook.feedOperations().post(userKey, "This is a message from Spring");
        String [] fields = {"id", "email"};
        User facebookUser = this.facebook.fetchObject(userKey, User.class, fields);

        this.socialUserService.registerOrLogin(facebookUser);
        this.connectionRepository.removeConnection(connectionKey);
        return "redirect:/";
    }
}

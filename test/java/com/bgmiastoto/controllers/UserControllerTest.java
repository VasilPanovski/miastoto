package com.bgmiastoto.controllers;

import com.bgmiastoto.entities.users.ApplicationUser;
import com.bgmiastoto.entities.users.SocialUser;
import com.bgmiastoto.models.bindingModels.UserRegistrationModel;
import com.bgmiastoto.services.ApplicationUserService;
import com.bgmiastoto.services.SocialUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private ApplicationUser service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void registerUser() throws Exception {
        mvc.perform(post("/users/register")
                    .param("username", "test@abv.bg")
                    .param("password", "122233334")
                    .param("confirmPassword", "122233334")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("base-layout"))
                .andExpect(model().hasErrors());

         ArgumentCaptor<UserRegistrationModel> captor = ArgumentCaptor.forClass(UserRegistrationModel.class);
         UserRegistrationModel bu = captor.getValue();
         assertEquals("test@abv.bg", bu.getName());
    }

}
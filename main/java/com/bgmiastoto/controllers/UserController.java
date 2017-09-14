package com.bgmiastoto.controllers;

import com.bgmiastoto.entities.users.ApplicationUser;
import com.bgmiastoto.errors.ErrorMessage;
import com.bgmiastoto.models.bindingModels.UserRegistrationModel;
import com.bgmiastoto.services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private ApplicationUserService applicationUserService;

    @GetMapping("register")
    public String getRegisterPage(@ModelAttribute UserRegistrationModel userRegistrationModel, Model model) {
        model.addAttribute("title", "Нов потребител");
        model.addAttribute("view", "register");
        model.addAttribute("userRegistrationModel", userRegistrationModel);
        return "base-layout";
    }

    @PostMapping("register")
    public String registerUser(@Valid UserRegistrationModel userRegistrationModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        this.applicationUserService.registerUser(userRegistrationModel);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("title", "Вход");
        if (error != null) {
            model.addAttribute("error", ErrorMessage.INVALID_CREDENTIALS);
        }

        model.addAttribute("view", "login");

        return "base-layout";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
}

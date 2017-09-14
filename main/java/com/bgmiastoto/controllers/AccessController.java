package com.bgmiastoto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessController {

    @GetMapping("/unauthorized")
    public String getNoAccess() {
        return "error/unauthorized";
    }
}

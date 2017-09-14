package com.bgmiastoto.controllers;

import com.bgmiastoto.models.viewModels.HomePlacesViewModel;
import com.bgmiastoto.models.viewModels.PlacesViewModel;
import com.bgmiastoto.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class HomeController {

    private final PlaceService placeService;

    @Autowired
    public HomeController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        Set<HomePlacesViewModel> homePlaces = this.placeService.findLastAddedPlaces();
        Set<HomePlacesViewModel> likedPlaces = this.placeService.findMostLikedPlaces();
        model.addAttribute("title", "Начална страница");
        model.addAttribute("view", "home");
        model.addAttribute("homePlaces", homePlaces);
        model.addAttribute("likedPlaces", likedPlaces);
        return "base-layout";
    }

    @GetMapping("about-us")
    public String getAboutAsPage(Model model) {
        model.addAttribute("title", "За нас");
        model.addAttribute("view", "about-us");
        return "base-layout";
    }
}

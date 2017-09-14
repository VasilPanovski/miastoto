package com.bgmiastoto.controllers;

import com.bgmiastoto.entities.places.enums.Category;
import com.bgmiastoto.exceptions.PlaceNotFoundException;
import com.bgmiastoto.models.bindingModels.PlaceRegistrationModel;
import com.bgmiastoto.models.viewModels.PlaceViewModel;
import com.bgmiastoto.models.viewModels.PlacesViewModel;
import com.bgmiastoto.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @ModelAttribute("categories")
    public Category[] getCategories() {
        return Category.values();
    }

    @GetMapping("add")
    public String getAddPlacePage(@ModelAttribute PlaceRegistrationModel placeRegistrationModel, Model model) {
        model.addAttribute("title", "Ново място");
        model.addAttribute("view", "add-place");
        model.addAttribute("placeRegistrationModel", placeRegistrationModel);
        return "base-layout";
    }

    @PostMapping("add")
    public String addPlace(@Valid @ModelAttribute PlaceRegistrationModel placeRegistrationModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-place";
        }

        placeService.save(placeRegistrationModel);
        return "redirect:/";
    }

    @GetMapping("{id}")
    public String getPlacePage(@PathVariable long id, Model model) {
        model.addAttribute("title", "Ново място");
        model.addAttribute("view", "add-places");
        return "base-layout";
    }

    @GetMapping(value = "place")
    public String getPlace(Model model) {
        model.addAttribute("title", "Място");
        model.addAttribute("view", "place");
        return "base-layout";
    }

    @GetMapping("all")
    public String getAllPlaces(Model model, @PageableDefault(size = 12) Pageable pageable) {
        Page<PlacesViewModel> places = this.placeService.findAll(pageable);
        model.addAttribute("title", "Местата");
        model.addAttribute("view", "places");
        model.addAttribute("places", places);

        return "base-layout";
    }

    @GetMapping("place/{id}")
    public String getPlaces(@PathVariable long id, Model model) {
        PlaceViewModel place = this.placeService.gePlaceById(id);
        String placeName = place.getName();
        model.addAttribute("title", placeName);
        model.addAttribute("view", "place");
        model.addAttribute("place", place);
        return "base-layout";
    }

    @ExceptionHandler(PlaceNotFoundException.class)
    public String catchPlaceNotFoundException() {
        return "error/place-not-found-exception";
    }
}

package com.bgmiastoto.controllers;

import com.bgmiastoto.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("map")
public class MapController {

    private final LocationService locationService;

    @Autowired
    public MapController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public String getAddPlacePage(Model model) {
        Set<float[]> markerLocations = this.locationService.getMarkerLocations();
        model.addAttribute("markerLocations", markerLocations);
        return "map";
    }
}

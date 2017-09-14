package com.bgmiastoto.controllers;

import com.bgmiastoto.models.bindingModels.EventRegistrationModel;
import com.bgmiastoto.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("add")
    public String getAddEventPage(@ModelAttribute EventRegistrationModel eventRegistrationModel, Model model) {
        model.addAttribute("title", "Ново събитие");
        model.addAttribute("view", "add-event");
        model.addAttribute("eventRegistrationModel", eventRegistrationModel);
        return "base-layout";
    }

    @PostMapping("add")
    public String addPlace(@Valid EventRegistrationModel eventRegistrationModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-event";
        }

        this.eventService.save(eventRegistrationModel);
        return "redirect:/";
    }
}

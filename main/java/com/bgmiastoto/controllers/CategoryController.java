package com.bgmiastoto.controllers;

import com.bgmiastoto.entities.places.enums.Category;
import com.bgmiastoto.exceptions.CategoryNotFoundException;
import com.bgmiastoto.models.viewModels.PlacesViewModel;
import com.bgmiastoto.services.CategoryService;
import com.bgmiastoto.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.StringJoiner;

@Controller
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAlbumPage(Model model) {
        model.addAttribute("title", "Категории");
        model.addAttribute("view", "categories");
        return "base-layout";
    }

    @GetMapping("{category}")
    public String getAlbumCategory(@PathVariable String category, @PageableDefault(size = 12) Pageable pageable, Model model) {
        Category requestCategory = handleRequest(category);
        Page<PlacesViewModel> places = this.categoryService.findAllByCategory(requestCategory, pageable);
        if (!places.hasContent()) {
            return "redirect:/places";
        }
        model.addAttribute("title", "Места по категория");
        model.addAttribute("view", "places");
        model.addAttribute("places", places);
        return "base-layout";
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public String catchBikeNotFoundException(){
        return "error/category-not-found-exception";
    }

    private Category handleRequest(String category) {
        switch (category) {
            case "relax":
                return Category.Отдих;
            case "nature":
                return Category.Природа;
            case "historicaly":
                return Category.Исторически;
            case "sport":
                return Category.Спорт;
            case "beach":
                return Category.Плаж;
            case "event":
                return Category.Събития;
            case "organic":
                return Category.БИО;
            case "others":
                return Category.Други;
            default: return null;
        }
    }

}

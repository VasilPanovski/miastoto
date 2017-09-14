package com.bgmiastoto.controllers;

import com.bgmiastoto.models.bindingModels.CommentBindingModel;
import com.bgmiastoto.models.bindingModels.DeletePlaceModel;
import com.bgmiastoto.models.bindingModels.LikeBindingModel;
import com.bgmiastoto.services.CommentService;
import com.bgmiastoto.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("places/place")
public class AdminController {

    private final CommentService commentService;

    private final PlaceService placeService;

    @Autowired
    public AdminController(CommentService commentService, PlaceService placeService) {
        this.commentService = commentService;
        this.placeService = placeService;
    }

    @PostMapping("places/delete/{placeId}")
    public String deletePlace(@PathVariable(required = false) Long placeId) {
        this.placeService.delete(placeId);
        return "redirect:/places/all";
    }

    @PutMapping("comment/add")
    public ResponseEntity saveComment(@RequestBody CommentBindingModel commentBindingModel){
        this.commentService.saveComment(commentBindingModel);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("likes")
    public ResponseEntity updateLikes(@RequestBody LikeBindingModel likeBindingModel){
        this.placeService.updateLikes(likeBindingModel);
        return new ResponseEntity(HttpStatus.OK);
    }
}

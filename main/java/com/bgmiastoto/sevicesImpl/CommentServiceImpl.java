package com.bgmiastoto.sevicesImpl;

import com.bgmiastoto.entities.places.Comment;
import com.bgmiastoto.entities.places.Place;
import com.bgmiastoto.models.bindingModels.CommentBindingModel;
import com.bgmiastoto.repositories.CommentRepository;
import com.bgmiastoto.repositories.PlaceRepository;
import com.bgmiastoto.services.CommentService;
import com.bgmiastoto.services.PlaceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final PlaceRepository placeRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PlaceService placeService, PlaceRepository placeRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.placeRepository = placeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void saveComment(CommentBindingModel commentBindingModel) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getPrincipal();
        auth.getCredentials();
        String username = (String) auth.getPrincipal();
        String content = commentBindingModel.getComment();
        Comment comment = new Comment(content, new Date(), username);
        this.commentRepository.save(comment);

        Place place = this.placeRepository.findOne(commentBindingModel.getPlaceId());
        place.getComments().add(comment);
        comment.setPlace(place);
    }
}

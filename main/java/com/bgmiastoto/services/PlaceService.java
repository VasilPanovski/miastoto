package com.bgmiastoto.services;

import com.bgmiastoto.entities.places.enums.Category;
import com.bgmiastoto.models.bindingModels.CommentBindingModel;
import com.bgmiastoto.models.bindingModels.DeletePlaceModel;
import com.bgmiastoto.models.bindingModels.LikeBindingModel;
import com.bgmiastoto.models.bindingModels.PlaceRegistrationModel;
import com.bgmiastoto.models.viewModels.HomePlacesViewModel;
import com.bgmiastoto.models.viewModels.PlaceViewModel;
import com.bgmiastoto.models.viewModels.PlacesViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;


public interface PlaceService {

    void save(PlaceRegistrationModel placeRegistrationModel);

    Page<PlacesViewModel> findAll(Pageable pageable);

    Set<HomePlacesViewModel> findLastAddedPlaces();

    Set<HomePlacesViewModel> findMostLikedPlaces();

    PlaceViewModel gePlaceById(long id);

    void updateLikes(LikeBindingModel likeBindingModel);

    void delete(Long id);
}

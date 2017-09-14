package com.bgmiastoto.sevicesImpl;

import com.bgmiastoto.entities.places.Place;
import com.bgmiastoto.entities.places.enums.Category;
import com.bgmiastoto.exceptions.CategoryNotFoundException;
import com.bgmiastoto.models.viewModels.PlacesViewModel;
import com.bgmiastoto.repositories.PlaceRepository;
import com.bgmiastoto.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final PlaceRepository repository;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(PlaceRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<PlacesViewModel> findAllByCategory(Category category, Pageable pageable) {
        Page<Place> places = this.repository.findAllByCategory(category, pageable);

        List<PlacesViewModel> placeViewModels = new ArrayList<>();

        PlacesViewModel placeViewModel;
        for (Place place : places) {
            placeViewModel = this.modelMapper.map(place, PlacesViewModel.class);
            placeViewModels.add(placeViewModel);
        }

        Page<PlacesViewModel> placeModels = new PageImpl<PlacesViewModel>(placeViewModels, pageable, places.getTotalElements());
        return placeModels;
    }
}

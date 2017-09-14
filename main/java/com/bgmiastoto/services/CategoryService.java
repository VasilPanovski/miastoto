package com.bgmiastoto.services;

import com.bgmiastoto.entities.places.enums.Category;
import com.bgmiastoto.models.viewModels.PlacesViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

//    List<PlacesViewModel> findAllByCategory(Category category);

    Page<PlacesViewModel> findAllByCategory(Category category, Pageable pageable);
}

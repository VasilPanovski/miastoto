package com.bgmiastoto.repositories;

import com.bgmiastoto.entities.places.Place;
import com.bgmiastoto.entities.places.enums.Category;
import com.bgmiastoto.models.viewModels.PlacesViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    Set<Place> findTop5ByOrderByIdDesc();

    Set<Place> findTop5ByOrderByLikesDesc();

    Page<Place> findAllByCategory(Category category, Pageable pageable);
    
}

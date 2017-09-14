package com.bgmiastoto.sevicesImpl;

import com.bgmiastoto.entities.location.Location;
import com.bgmiastoto.entities.places.Comment;
import com.bgmiastoto.entities.places.Place;
import com.bgmiastoto.entities.places.enums.Category;
import com.bgmiastoto.exceptions.PlaceNotFoundException;
import com.bgmiastoto.models.bindingModels.CommentBindingModel;
import com.bgmiastoto.models.bindingModels.DeletePlaceModel;
import com.bgmiastoto.models.bindingModels.LikeBindingModel;
import com.bgmiastoto.models.bindingModels.PlaceRegistrationModel;
import com.bgmiastoto.models.viewModels.HomePlacesViewModel;
import com.bgmiastoto.models.viewModels.PlaceViewModel;
import com.bgmiastoto.models.viewModels.PlacesViewModel;
import com.bgmiastoto.repositories.PlaceRepository;
import com.bgmiastoto.services.PlaceService;
import com.bgmiastoto.utils.DropBoxUploader;
import com.dropbox.core.DbxException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    private final ModelMapper modelMapper;

    private final DropBoxUploader dropBoxUploader;

    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository, ModelMapper modelMapper, DropBoxUploader dropBoxUploader) {
        this.placeRepository = placeRepository;
        this.modelMapper = modelMapper;
        this.dropBoxUploader = dropBoxUploader;
    }

    @Override
    public void save(PlaceRegistrationModel placeRegistrationModel) {
        Place place = new Place();
        mapModelToPlace(place, placeRegistrationModel);
        this.placeRepository.save(place);
    }

    @Override
    public Page<PlacesViewModel> findAll(Pageable pageable) {
        Page<Place> places = this.placeRepository.findAll(pageable);
        List<PlacesViewModel> placeViewModels = new ArrayList<>();

        PlacesViewModel placeViewModel;
        for (Place place : places) {
            placeViewModel = this.modelMapper.map(place, PlacesViewModel.class);
            placeViewModels.add(placeViewModel);
        }

        Page<PlacesViewModel> placeModels = new PageImpl<PlacesViewModel>(placeViewModels, pageable, places.getTotalElements());
        return placeModels;
    }

    @Override
    public Set<HomePlacesViewModel> findLastAddedPlaces() {
        Set<Place> places = this.placeRepository.findTop5ByOrderByIdDesc();

        Set<HomePlacesViewModel> homePlacesViewModels = new HashSet<>();
        for (Place place : places) {
            homePlacesViewModels.add(this.modelMapper.map(place, HomePlacesViewModel.class));
        }
        return homePlacesViewModels;
    }

    @Override
    public Set<HomePlacesViewModel> findMostLikedPlaces() {
        Set<Place> places = this.placeRepository.findTop5ByOrderByLikesDesc();

        Set<HomePlacesViewModel> homePlacesViewModels = new HashSet<>();
        for (Place place : places) {
            homePlacesViewModels.add(this.modelMapper.map(place, HomePlacesViewModel.class));
        }
        return homePlacesViewModels;
    }

    @Override
    public PlaceViewModel gePlaceById(long id) {
        Place place = this.placeRepository.findOne(id);
        if (place == null) {
            throw new PlaceNotFoundException();
        }

        PlaceViewModel placeViewModel = this.modelMapper.map(place, PlaceViewModel.class);
        return placeViewModel;
    }

    @Override
    @Transactional
    public void updateLikes(LikeBindingModel likeBindingModel) {
        Place place = this.placeRepository.findOne(likeBindingModel.getPlaceId());
        place.setLikes(likeBindingModel.getLikes());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Place place = this.placeRepository.findOne(id);
        if (place == null) {
            throw new PlaceNotFoundException();
        }
        this.placeRepository.delete(place);
    }


    private void mapModelToPlace(Place place, PlaceRegistrationModel placeRegistrationModel) {
        place.setName(placeRegistrationModel.getName());
        place.setIsNotAccessible(placeRegistrationModel.getIsNotAccessible());
        place.setDescription(placeRegistrationModel.getDescription());
        place.setCategory(Category.valueOf(placeRegistrationModel.getCategory()));
        Location location = new Location();
        location.setAddress(placeRegistrationModel.getAddress());
        location.setLatitude(placeRegistrationModel.getLatitude());
        location.setLongitude(placeRegistrationModel.getLongitude());
        place.setLocation(location);
        try {
            String imageUrl = DropBoxUploader.uploadImage(placeRegistrationModel.getMainImg().getOriginalFilename(), placeRegistrationModel.getMainImg());
            place.setMainImage(imageUrl);
            for (MultipartFile multipartFile : placeRegistrationModel.getModelImg()) {
                place.getImages().add(DropBoxUploader.uploadImage(multipartFile.getOriginalFilename(), multipartFile));
            }
        } catch (DbxException e) {
            e.printStackTrace();
        }
//        byte[] fileContent = null;
//        String filePath = null;
//        try {
//            fileContent = placeRegistrationModel.getMainImg().getBytes();
//            filePath = FilesIO.writeFile(fileContent, placeRegistrationModel.getMainImg().getOriginalFilename());
//            place.setMainImage(filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (placeRegistrationModel.getModelImg().size() > 0) {
//            for (MultipartFile image : placeRegistrationModel.getModelImg()) {
//                if (image.equals(place.getMainImage())) {
//                    continue;
//                }
//
//                try {
//                    fileContent = image.getBytes();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                filePath = FilesIO.writeFile(fileContent, image.getOriginalFilename());
//                place.setMainImage(filePath);
//                place.getImages().add(filePath);
//            }
//        }
    }
}
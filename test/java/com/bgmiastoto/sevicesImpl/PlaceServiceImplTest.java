package com.bgmiastoto.sevicesImpl;

import com.bgmiastoto.entities.location.Location;
import com.bgmiastoto.entities.places.Place;
import com.bgmiastoto.entities.places.enums.Category;
import com.bgmiastoto.exceptions.PlaceNotFoundException;
import com.bgmiastoto.models.viewModels.PlaceViewModel;
import com.bgmiastoto.repositories.PlaceRepository;
import com.bgmiastoto.services.PlaceService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PlaceServiceImplTest {

    public static final long VALID_ID = 1l;
    public static final long INVALID_ID = -1l;
    public static final String PLACE_NAME = "Varna";
    public static final String PLACE_ADDRESS = "Palm beach";
    public static final String MAIN_IMAGE = "beach.png";
    public static final String DESCRIPTION = "Very nice city with very rich people on Wall Street";
    public static final float LATITUDE = 42.005f;
    public static final float LONGITUDE = 41.305f;

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceService placeService;

    @Before
    public void setUp() throws Exception {
        Place place = new Place();
        place.setId(VALID_ID);
        place.setName(PLACE_NAME);
        place.setCategory(Category.Отдих);
        place.setMainImage(MAIN_IMAGE);
        Location location = new Location();
        location.setAddress(PLACE_ADDRESS);
        location.setLatitude(LATITUDE);
        location.setLongitude(LONGITUDE);
        place.setLocation(location);
        place.setDescription(DESCRIPTION);
        when(this.placeRepository.findOne(VALID_ID)).thenReturn(place);

    }

    @Test
    public void findByGivenId_ShouldReturnValidViewModel() throws Exception {
        //Action
        PlaceViewModel placeViewModel = this.placeService.gePlaceById(VALID_ID);

        //Assert Id
        Assert.assertEquals(VALID_ID, placeViewModel.getId());
        //Assert Name
        Assert.assertEquals(PLACE_NAME, placeViewModel.getName());
        //Assert Location
        Assert.assertEquals(DESCRIPTION, placeViewModel.getDescription());
    }

    @Test
    public void findByGivenValidPlace_ShouldCallRepositoryMethodFindById() throws Exception {
        //Action
        this.placeService.gePlaceById(VALID_ID);

        //Assert DB Call
        verify (this.placeRepository, times(1)).findOne(VALID_ID);
    }

    @Test(expected = PlaceNotFoundException.class)
    public void findByGivenInvalidId_ShouldThrowException() throws Exception {
        //Action
        this.placeService.gePlaceById(INVALID_ID);
    }

}
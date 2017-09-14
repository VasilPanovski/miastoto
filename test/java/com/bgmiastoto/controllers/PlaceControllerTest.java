package com.bgmiastoto.controllers;

import com.bgmiastoto.entities.location.Location;
import com.bgmiastoto.entities.places.enums.Category;
import com.bgmiastoto.models.viewModels.PlaceViewModel;
import com.bgmiastoto.services.PlaceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(PlaceController.class)
@ActiveProfiles("test")
public class PlaceControllerTest {

    public static final long PLACE_ID = 1;
    public static final String PLACE_NAME = "Varna";
    public static final String PLACE_ADDRESS = "Palm beach";
    public static final String MAIN_IMAGE = "beach.png";
    public static final String DESCRIPTION = "Very nice city with very rich people on Wall Street";
    public static final float LATITUDE = 42.005f;
    public static final float LONGITUDE = 41.305f;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlaceService placeService;

    @Before
    public void setUp() throws Exception {
        PlaceViewModel place = new PlaceViewModel();
        place.setId(PLACE_ID);
        place.setName(PLACE_NAME);
        place.setCategory(Category.Отдих);
        place.setMainImage(MAIN_IMAGE);
        Location location = new Location();
        location.setAddress(PLACE_ADDRESS);
        location.setLatitude(LATITUDE);
        location.setLongitude(LONGITUDE);
        place.setLocation(location);
        place.setDescription(DESCRIPTION);
        Mockito.when(this.placeService.gePlaceById(PLACE_ID)).thenReturn(place);

    }

    @Test
    public void showPlaceModelByGivenPlaceData_ShouldReturnPlaceViewModelCorrectly() throws Exception {
        //Acction
        this.mvc
                .perform(get("/places/place/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("base-layout"))
                .andExpect(model().attribute("place", hasProperty("id", is(PLACE_ID))))
                .andExpect(model().attribute("place", hasProperty("name", is(PLACE_NAME))));

        Mockito.verify(this.placeService, Mockito.times(1)).gePlaceById(PLACE_ID);
    }

}
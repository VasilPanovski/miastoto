package com.bgmiastoto.repositories;

import com.bgmiastoto.entities.location.Location;
import com.bgmiastoto.entities.places.Place;
import com.bgmiastoto.entities.places.enums.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class PlaceRepositoryTest {

    public static final String PLACE_NAME = "Varna";
    public static final String PLACE_ADDRESS = "Palm beach";
    public static final String MAIN_IMAGE = "beach.png";
    public static final String DESCRIPTION = "Very nice city with very rich people on Wall Street";
    public static final float LATITUDE = 42.005f;
    public static final float LONGITUDE = 41.305f;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private PlaceRepository placeRepository;

    @Before
    public void setUp() throws Exception {
        Place place = new Place();
        place.setName(PLACE_NAME);
        place.setCategory(Category.Отдих);
        place.setMainImage(MAIN_IMAGE);
        Location location = new Location();
        location.setAddress(PLACE_ADDRESS);
        location.setLatitude(LATITUDE);
        location.setLongitude(LONGITUDE);
        place.setLocation(location);
        place.setDescription(DESCRIPTION);
        this.em.persist(place);

    }

    @Test
    public void findTop5PlacesByOrderByIdInDescsendingOrder_ShouldReturn5Places() throws Exception {
        Set<Place> places = this.placeRepository.findTop5ByOrderByIdDesc();

        Assert.assertEquals(1, places.size());

        Place place = (Place) places.toArray()[0];
        Assert.assertEquals(PLACE_NAME, place.getName());

    }

}
package com.bgmiastoto.sevicesImpl;

import com.bgmiastoto.entities.location.Location;
import com.bgmiastoto.repositories.LocationRepository;
import com.bgmiastoto.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Set<float[]> getMarkerLocations() {
        List<Location> locationSet = this.locationRepository.findAll();
        Set<float[]> markerLocations= new HashSet<>();
        float[] currentLocation = null;
        for (Location location : locationSet) {
            currentLocation = new float[2];
            currentLocation[0] = location.getLatitude();
            currentLocation[1] = location.getLongitude();
            markerLocations.add(currentLocation);
        }
        return markerLocations;
    }
}

package com.bgmiastoto.entities.location;

import com.bgmiastoto.entities.events.Event;
import com.bgmiastoto.entities.places.Place;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String address;

    private float latitude;

    private float longitude;

    @OneToOne(mappedBy = "location")
    private Place place;

    @OneToMany(mappedBy = "location")
    private Set<Event> events;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }


}

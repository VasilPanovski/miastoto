package com.bgmiastoto.models.bindingModels;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EventRegistrationModel {

    @Size(min = 3, max = 50, message = "Наименованието на събитието не може да съдържа по-малко от 3 и повече от 50 символа!")
    private String name;

    private String address;

    @NotNull
    private float latitude;

    private float longitude;

    @Size(min = 20, max = 300, message = "Описанието не може да бъде по кратко от 20 и по-дълго от 300 символа!")
    private String description;

    @NotNull(message = "Не е въведена дата.")
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date eventDate;

    @NotNull(message = "Не е избрана заглавна снимка на събитието.")
    private MultipartFile mainImage;

    private Set<MultipartFile> images;

    public EventRegistrationModel() {
        this.images = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public MultipartFile getMainImage() {
        return mainImage;
    }

    public void setMainImage(MultipartFile mainImage) {
        this.mainImage = mainImage;
    }

    public Set<MultipartFile> getImages() {
        return images;
    }

    public void setImages(Set<MultipartFile> images) {
        this.images = images;
    }
}

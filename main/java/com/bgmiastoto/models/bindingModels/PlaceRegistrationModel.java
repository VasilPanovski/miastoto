package com.bgmiastoto.models.bindingModels;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class PlaceRegistrationModel {

    @Size(min = 3, max = 50, message = "Името на мястото не може да съдържа по-малко от 3 и повече от 50 символа!")
    private String name;

    @NotBlank(message = "Не сте избрали категория на мястото!")
    private String category;

    @Size(min = 20, max = 300, message = "Описанието не може да бъде по кратко от 20 и по-дълго от 300 символа!")
    private String description;

    private String address;

    @NotNull
    private float latitude;

    private float longitude;

    private boolean isNotAccessible;

    @NotNull(message = "Не е избрана заглавна снимка за мястото.")
    private MultipartFile mainImg;

    private Set<MultipartFile> modelImg;

    public PlaceRegistrationModel() {
        this.modelImg = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean getIsNotAccessible() {
        return isNotAccessible;
    }

    public void setIsNotAccessible(boolean accessible) {
        isNotAccessible = accessible;
    }

    public MultipartFile getMainImg() {
        return mainImg;
    }

    public void setMainImg(MultipartFile mainImg) {
        this.mainImg = mainImg;
    }

    public Set<MultipartFile> getModelImg() {
        return modelImg;
    }

    public void setModelImg(Set<MultipartFile> modelImg) {
        this.modelImg = modelImg;
    }
}

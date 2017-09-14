package com.bgmiastoto.models.viewModels;

import com.bgmiastoto.entities.location.Location;
import com.bgmiastoto.entities.places.Comment;
import com.bgmiastoto.entities.places.enums.Category;

import javax.persistence.Column;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlaceViewModel {
    private long id;
    private String name;
    private String description;
    private boolean isNotAccessible;
    private Category category;
    private Location location;
    private String mainImage;
    private Set<String> images;
    private List<Comment> comments;
    private Date uploadDate;
    private int likes;

    public PlaceViewModel() {
        this.images = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNotAccessible() {
        return isNotAccessible;
    }

    public void setNotAccessible(boolean notAccessible) {
        isNotAccessible = notAccessible;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

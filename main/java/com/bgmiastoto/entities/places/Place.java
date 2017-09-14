package com.bgmiastoto.entities.places;

import com.bgmiastoto.entities.location.Location;
import com.bgmiastoto.entities.places.enums.Category;

import javax.persistence.*;
import java.util.*;

@Entity(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    private boolean isNotAccessible;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "main_image", columnDefinition = "TEXT")
    private String mainImage;

    @Column(name = "place_images", columnDefinition = "TEXT")
    @ElementCollection
    private Set<String> images;

    @OneToMany(mappedBy = "place")
    private List<Comment> comments;

    @Column(name = "upload_date")
    private Date uploadDate;

    private int likes;

    public Place() {
        this.images = new HashSet<>();
        this.comments = new ArrayList<>();
        this.uploadDate = new Date();
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

    public boolean getIsNotAccessible() {
        return isNotAccessible;
    }

    public void setIsNotAccessible(boolean accessible) {
        this.isNotAccessible = accessible;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isNotAccessible() {
        return isNotAccessible;
    }

    public void setNotAccessible(boolean notAccessible) {
        isNotAccessible = notAccessible;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

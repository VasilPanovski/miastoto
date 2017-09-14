package com.bgmiastoto.models.bindingModels;

public class LikeBindingModel {

    private long placeId;

    private int likes;

    public LikeBindingModel() {
    }

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}

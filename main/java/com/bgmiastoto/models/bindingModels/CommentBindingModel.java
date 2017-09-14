package com.bgmiastoto.models.bindingModels;


public class CommentBindingModel {
    private long placeId;
    private String comment;

    public CommentBindingModel() {
    }

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

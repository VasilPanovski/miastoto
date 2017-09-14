package com.bgmiastoto.entities.places;

import com.bgmiastoto.entities.users.User;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    private Date date;

    private String user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Place place;

    public Comment() {
    }

    public Comment(String content, Date date, String user) {
        this.content = content;
        this.date = date;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}

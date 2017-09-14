package com.bgmiastoto.entities.interseptors.log;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "log_actions")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String action;

    @Column(name = "duration")
    private long actionTime;

    private Date date;

    public Log() {
    }

    public Log(String action, long actionTime) {
        this.action = action;
        this.actionTime = actionTime;
        this.date = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getActionTime() {
        return actionTime;
    }

    public void setActionTime(long actionTime) {
        this.actionTime = actionTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

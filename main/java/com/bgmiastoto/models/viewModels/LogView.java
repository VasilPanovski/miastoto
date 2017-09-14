package com.bgmiastoto.models.viewModels;

import java.util.Date;

public class LogView {

    private String action;

    private long actionTime;

    private Date date;

    public LogView(String action, long actionTime) {
        this.action = action;
        this.actionTime = actionTime;
        this.date = new Date();
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

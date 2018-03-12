package com.volunteer.aly.volunteerAPP;

/**
 * Created by aly on 3/11/18.
 */

public class Visit {
    String date,time,count,relashionship,volunteer;

    public Visit() {
    }

    public Visit(String date, String time, String count, String relashionship, String volunteer) {
        this.date = date;
        this.time = time;
        this.count = count;
        this.relashionship = relashionship;
        this.volunteer = volunteer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getRelashionship() {
        return relashionship;
    }

    public void setRelashionship(String relashionship) {
        this.relashionship = relashionship;
    }

    public String getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(String volunteer) {
        this.volunteer = volunteer;
    }
}

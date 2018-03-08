package com.example.aly.myapplication;

/**
 * Created by aly on 3/6/18.
 */

public  class Feeds {
     String name,pname,room,feed;

    public Feeds(String name, String pname, String room, String feed) {
        this.name = name;
        this.pname = pname;
        this.room = room;
        this.feed = feed;
    }
    public Feeds(){}

    public String getName() {
        return name;
    }

    public String getPname() {
        return pname;
    }

    public String getRoom() {
        return room;
    }

    public String getFeed() {
        return feed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }
}
